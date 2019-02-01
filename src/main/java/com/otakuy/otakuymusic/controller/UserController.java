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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
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

    @PostMapping("/login")
    public Mono<ResponseEntity<Result<String>>> login(@RequestBody AuthRequest authRequest) {
        return userService.findByUsername(authRequest.getUsername()).map(userDetails -> {
            if (passwordEncoder.encode(authRequest.getPassword()).equals(userDetails.getPassword())) {
                return ResponseEntity.ok(new Result<>("ok", jwtUtil.generateToken(userDetails)));
            }
            throw new CheckException(new Result<>(HttpStatus.BAD_REQUEST, "用户名或密码错误"));
        });
    }

    @GetMapping("/check/usernames")
    public Mono<ResponseEntity<Result<String>>> checkByUsername(@RequestParam String username) {
        return userService.findByUsername(username).hasElement().map(exit -> {
            if (exit)
                throw new CheckException(new Result<>(HttpStatus.BAD_REQUEST, "该用户名已被注册"));
            return ResponseEntity.ok(new Result<>("该用户名可以被注册"));
        });
    }

    @GetMapping("/check/emails")
    public Mono<ResponseEntity<Result<String>>> checkByEmail(@RequestParam String email) {
        return userService.findByEmail(email).hasElement().map(exit -> {
            if (exit)
                throw new CheckException(new Result<>(HttpStatus.BAD_REQUEST, "该邮箱已被注册"));
            return ResponseEntity.ok(new Result<>("该邮箱可以被注册"));
        });
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<Result<?>>> userRegister(@RequestHeader("verificationCode") String verificationCode, @RequestHeader("verificationCodeId") String verificationCodeId, @Valid @RequestBody User user) {
        return verificationCodeService.checkVerificationCode(new VerificationCodeUtil.VerificationCode(verificationCodeId, verificationCode)).hasElement(
        ).flatMap(exist ->
        {
            if (!exist) //抛异常
                throw new CheckException(new Result<>(HttpStatus.BAD_REQUEST, "验证码失效或错误"));
            return userService.findByUsernameOrEmail(user.getUsername(), user.getEmail()).hasElements().flatMap(
                    userExist -> {
                        if (userExist)
                            throw new CheckException(new Result<>(HttpStatus.CONFLICT , "用户名或邮箱已被注册"));
                      /*  try {
                            user.getClass().getMethod("setUsername",String.class).invoke(user,"aas");
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                            e.printStackTrace();
                        }*/
                        user.setId(null);
                        user.setRole(Arrays.asList(Role.ROLE_USER));
                        user.setPassword(passwordEncoder.encode(user.getPassword()));
                        return userService.userRegister(user).map(u -> ResponseEntity.ok(new Result<>("注册完成",user)));
                    });
        });
    }

}
