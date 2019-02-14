package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.exception.CheckException;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.service.AlbumService;
import com.otakuy.otakuymusic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Validated
@RestController
@RequestMapping("/check")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CheckController {
    private final UserService userService;
    private final AlbumService albumService;

    //检索是否存在重复用户名
    @GetMapping("/usernames")
    public Mono<ResponseEntity<Result<Boolean>>> checkUserByUsername(@RequestParam @NotBlank(message = "用户名不能为空") String username) {
        return userService.findByUsername(username).hasElement().map(exit -> {
            if (exit)
                throw new CheckException(new Result<>(HttpStatus.BAD_REQUEST, "用户名校验不通过(已存在相同用户名)", false));
            return ResponseEntity.ok(new Result<>("用户名有效", true));
        });
    }

    //检索是否存在重复邮箱
    @GetMapping("/emails")
    public Mono<ResponseEntity<Result<Boolean>>> checkUserByEmail(@RequestParam @Email String email) {
        return userService.findByEmail(email).hasElement().map(exit -> {
            if (exit)
                throw new CheckException(new Result<>(HttpStatus.BAD_REQUEST, "邮箱校验不通过(已存在相同邮箱)", false));
            return ResponseEntity.ok(new Result<>("邮箱有效", true));
        });
    }

    //检索是否存在重复专辑名
    @GetMapping("/albums")
    public Mono<ResponseEntity<Result<Boolean>>> checkAlbumByTitle(@RequestParam String title) {
        return albumService.findAllByTitleAndStatusNotReject(title).hasElements().map(exit -> {
            if (exit)
                throw new CheckException(new Result<>(HttpStatus.BAD_REQUEST, "专辑名校验不通过(已存在相同专辑名)", false));
            return ResponseEntity.ok(new Result<>("专辑名有效", true));
        });
    }
}
