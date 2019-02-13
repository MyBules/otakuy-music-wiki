package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.Star;
import com.otakuy.otakuymusic.service.AlbumService;
import com.otakuy.otakuymusic.service.StarService;
import com.otakuy.otakuymusic.service.UserService;
import com.otakuy.otakuymusic.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class StarController {
    private final StarService starService;
    private final AlbumService albumService;
    private final UserService userService;
    private final JWTUtil jwtUtil;

    //打赏
    @PostMapping("/album/{album_id}/star")
    public Mono<ResponseEntity<Result<Star>>> create(@RequestHeader("Authorization") String token, @PathVariable("album_id") String album_id, @RequestBody Star star) {
        return userService.findStarById(jwtUtil.getId(token)).flatMap(sum -> {
            if (sum >= star.getNum())
                return starService.create(star).map(save -> ResponseEntity.ok(new Result<>("打赏成功", save)));
            return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result<>("拥有star数不足")));
        });
    }
    //按专辑获取打赏列表
    @GetMapping("/album/{album_id}/star")
    public Mono<ResponseEntity<Result<List<Star>>>> get(@PathVariable("album_id") String album_id) {
        return albumService.existByIdAndStatusActive(album_id).flatMap(exist -> {
            if (exist)
                return starService.findAllByStarAt(album_id).collectList().map(list -> ResponseEntity.ok(new Result<>("拉取成功", list)));
            return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result<>("专辑不存在")));
        });
    }
}
