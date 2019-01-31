package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.User;
import com.otakuy.otakuymusic.model.security.AuthRequest;
import com.otakuy.otakuymusic.model.security.Role;
import com.otakuy.otakuymusic.service.UserService;
import com.otakuy.otakuymusic.service.VerificationCodeService;
import com.otakuy.otakuymusic.util.JWTUtil;
import com.otakuy.otakuymusic.util.PBKDF2Encoder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

//@RestController
//@RequestMapping("/users")
@Log4j2
public class UserController1 {

/*    private final JWTUtil jwtUtil;
    private final PBKDF2Encoder passwordEncoder;
    private final UserService userService;
    private final VerificationCodeService verificationCodeService;

    @Autowired
    public UserController1(JWTUtil jwtUtil, PBKDF2Encoder passwordEncoder, UserService userService, VerificationCodeService verificationCodeService) {
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
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Result<>("failed", "用户名或密码错误"));
            }
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Result<>("failed", "用户名或密码错误")));
    }

    @GetMapping("/usernames")
    public Mono<ResponseEntity<Result<String>>> checkByUsername(@RequestParam String username) {
        return userService.checkUserExitByUsername(username).map(exit -> {
            if (exit)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result<>("该用户名已被注册", "failed"));
            else
                return ResponseEntity.ok(new Result<>("该用户名可以被注册", "success"));
        });
    }

    @GetMapping("/emails")
    public Mono<ResponseEntity<Result<String>>> checkByEmail(@RequestParam String email) {
        return userService.checkUserExitByEmail(email).map(exit -> {
            if (exit)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result<>("该邮箱已被注册", "failed"));
            else
                return ResponseEntity.ok(new Result<>("该邮箱可以被注册", "success"));
        });
    }

    @PostMapping()
    public Mono<ResponseEntity<Result<String>>> userRegister(@RequestHeader("verificationCode") String verificationCode, @RequestHeader("verificationCodeId") String verificationCodeId, @RequestBody User user) {
        System.out.println(12);

*//*        return verificationCodeService.checkVerificationCode(verificationCodeId).map(code -> {
            Result<String> result = new Result<>();
            if (verificationCode.equals(code.getCode())) {
                //检查用户名与邮箱是否有效
                 userService.checkUserExitByEmail(user.getEmail()).map(emailExist -> {
                            if (!emailExist) {
                                userService.checkUserExitByUsername(user.getUsername()).hasElement().map(usernameExist -> {
                                    if (!usernameExist) {
                                        user.setId(null);
                                        user.setRole(Arrays.asList(Role.ROLE_USER));
                                        user.setPassword(passwordEncoder.encode(user.getPassword()));
                                        userService.userRegister(user);
                                        result.set(HttpStatus.OK,"success", "用户创建成功");
                                        return usernameExist;
                                    }
                                    result.set(HttpStatus.BAD_REQUEST,"failed", "用户名重复");
                                    return usernameExist;
                                });
                            }
                            result.set(HttpStatus.BAD_REQUEST,"failed", "邮箱重复");
                            return emailExist;
                        });
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
            }
            result.set(HttpStatus.BAD_REQUEST,"failed", "验证码错误");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result);
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Result<>("failed", "验证码过期")));*//*
return null;

    }

    public static void main(String[] args) {

    }*/
}
