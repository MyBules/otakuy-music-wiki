package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.exception.CheckException;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.User;
import com.otakuy.otakuymusic.model.security.AuthRequest;
import com.otakuy.otakuymusic.model.security.Role;
import com.otakuy.otakuymusic.service.UserService;
import com.otakuy.otakuymusic.service.VerificationCodeService;
import com.otakuy.otakuymusic.util.JWTUtil;
import com.otakuy.otakuymusic.util.PBKDF2Encoder;
import com.otakuy.otakuymusic.util.VerificationCodeUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Arrays;

@RestController
@Log4j2
public class UserController {

    private final JWTUtil jwtUtil;
    private final PBKDF2Encoder passwordEncoder;
    private final UserService userService;
    private final VerificationCodeService verificationCodeService;

    @Autowired
    public UserController(JWTUtil jwtUtil, PBKDF2Encoder passwordEncoder, UserService userService, VerificationCodeService verificationCodeService) {
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.verificationCodeService = verificationCodeService;
    }

    //用户登录
    @PostMapping("/login")
    public Mono<ResponseEntity<Result<String>>> login(@RequestBody AuthRequest authRequest) {
        return userService.findByUsername(authRequest.getUsername()).map(userDetails -> {
            if (passwordEncoder.encode(authRequest.getPassword()).equals(userDetails.getPassword())) {
                return ResponseEntity.ok(new Result<>("ok", jwtUtil.generateToken(userDetails)));
            }
            throw new CheckException(new Result<>(HttpStatus.BAD_REQUEST, "用户名或密码错误"));
        });
    }

    //用户注册
    @PostMapping("/register")
    public Mono<ResponseEntity<Result<?>>> userRegister(@RequestHeader("verificationCode") String verificationCode, @RequestHeader("verificationCodeId") String verificationCodeId, @Valid @RequestBody User user) {
        return verificationCodeService.checkVerificationCode(new VerificationCodeUtil.VerificationCode(verificationCodeId, verificationCode)).hasElement(
        ).flatMap(exist ->
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

    //按照id查看用户信息
    @GetMapping("/users/{user_id}")
    public Mono<ResponseEntity<Result<User>>> findById(@PathVariable("user_id") String user_id) {
        return userService.findById(user_id).map(user -> ResponseEntity.ok(new Result<>("注册完成", user))).defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result<>("用户不存在", null)));
    }
    //修改用户信息

}
