package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.exception.CheckException;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.User;
import com.otakuy.otakuymusic.model.security.AuthRequest;
import com.otakuy.otakuymusic.model.security.Role;
import com.otakuy.otakuymusic.service.EmailService;
import com.otakuy.otakuymusic.service.UserService;
import com.otakuy.otakuymusic.service.VerificationCodeService;
import com.otakuy.otakuymusic.util.JWTUtil;
import com.otakuy.otakuymusic.util.PBKDF2Encoder;
import com.otakuy.otakuymusic.util.UserUtil;
import com.otakuy.otakuymusic.util.VerificationCodeUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;

@Log4j2
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final JWTUtil jwtUtil;
    private final UserUtil userUtil;
    private final PBKDF2Encoder passwordEncoder;
    private final UserService userService;
    private final EmailService emailService;
    private final VerificationCodeService verificationCodeService;

    //用户登录
    @PostMapping("/login")
    public Mono<ResponseEntity<Result<User>>> login(@Validated @RequestBody AuthRequest authRequest) {
        return userService.findByUsername(authRequest.getUsername()).map(userDetails -> {
            if (passwordEncoder.encode(authRequest.getPassword()).equals(userDetails.getPassword())) {
                return ResponseEntity.ok().header("Authorization", jwtUtil.generateToken(userDetails)).body(new Result<>("登录成功", userDetails));
            }
            throw new CheckException(new Result<>(HttpStatus.BAD_REQUEST, "登录失败:用户名或密码错误"));
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result<>("用户不存在")));
    }

    //用户注册
    @PostMapping("/register")
    public Mono<ResponseEntity<Result<?>>> userRegister(@RequestHeader("verificationCode") String verificationCode, @RequestHeader("verificationCodeId") String verificationCodeId, @Valid @RequestBody User user) {
        return verificationCodeService.checkVerificationCode(new VerificationCodeUtil.VerificationCode(verificationCodeId, verificationCode)).hasElement().flatMap(exist ->
        {
            if (!exist)
                throw new CheckException(new Result<>(HttpStatus.BAD_REQUEST, "验证码失效或错误"));
            return userService.findByUsernameOrEmail(user.getUsername(), user.getEmail()).hasElements().flatMap(
                    userExist -> {
                        if (userExist)
                            throw new CheckException(new Result<>(HttpStatus.CONFLICT, "用户名或邮箱已被注册"));
                        user.setId(null);
                        user.setEnabled(true);
                        user.setRole(Arrays.asList(Role.ROLE_USER));
                        user.setPassword(passwordEncoder.encode(user.getPassword()));
                        user.setAvatar("https://avatar.otakuy.com/default.png");
                        user.setStar(0);
                        return userService.userRegister(user).map(u -> ResponseEntity.ok(new Result<>("注册完成", user)));
                    });
        });
    }

    //更改头像
    @PostMapping(value = "/users/avatars", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<Result<String>>> uploadAvatar(@RequestHeader("Authorization") String token, @RequestPart("file") FilePart filePart) throws IOException {
        return Mono.just(ResponseEntity.ok(new Result<>("上传头像成功", userService.uploadAvatar(jwtUtil.getId(token), filePart))));
    }

    //申请重置密码
    @GetMapping(value = "/forgetPassword")
    public Mono<ResponseEntity<Result<String>>> requestModifyPassword(@RequestParam String email) throws IOException {
        return userService.findByEmail(email).hasElement().flatMap(exit -> {
            if (!exit)
                throw new CheckException(new Result<>(HttpStatus.BAD_REQUEST, "邮箱不存在"));
            return verificationCodeService.getPasswordVerificationCode(email).flatMap(verificationCode -> {
                try {
                    return emailService.sendVerificationEmail(email, verificationCode).map(result -> ResponseEntity.ok(new Result<>("发送验证邮件成功")));
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result<>("发送验证邮件失败")));
            });
        });
    }

    //更改密码
    @PutMapping(value = "/users/password")
    public Mono<ResponseEntity<Result<String>>> modifyPassword(@RequestParam String email, @RequestParam String verificationCode, @RequestParam String verificationCodeId, @RequestParam String password) throws IOException {
        return verificationCodeService.checkPasswordVerificationCode(new VerificationCodeUtil.VerificationCode(verificationCodeId, verificationCode, email)).hasElement().flatMap(exist -> {
            if (!exist)
                throw new CheckException(new Result<>(HttpStatus.BAD_REQUEST, "重置密码失败:链接错误或失效"));
            return userService.modifyPassword(email, password).map(user -> ResponseEntity.ok(new Result<>("修改密码成功")));
        });
    }

    //按照id查看用户信息
    @GetMapping("/users/{user_id}")
    public Mono<ResponseEntity<Result<User>>> findById(@PathVariable("user_id") String user_id) {
        return userService.findById(user_id).map(user -> ResponseEntity.ok(new Result<>("成功拉取" + user.getUsername() + "的个人信息", user))).defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result<>("用户不存在", null)));
    }

    //修改用户信息
    @PutMapping("/users/{user_id}")
    public Mono<ResponseEntity<Result<User>>> updatePersonalInformation(@RequestHeader("Authorization") String token, @Validated @RequestBody User user) {
        return userService.findById(jwtUtil.getId(token)).flatMap(oldUser ->
                userService.updatePersonalInformation(userUtil.update(oldUser, user)).map(newUser -> ResponseEntity.ok(new Result<>("更新完成", newUser)))
        ).defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result<>("用户不存在", null)));
    }

}
