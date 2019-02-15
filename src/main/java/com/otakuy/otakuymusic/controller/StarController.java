package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Notification;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.Star;
import com.otakuy.otakuymusic.service.AlbumService;
import com.otakuy.otakuymusic.service.NotificationService;
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
    private final NotificationService notificationService;
    private final JWTUtil jwtUtil;

    //打赏
    @PostMapping("/albums/{album_id}/star")
    public Mono<ResponseEntity<Result<Star>>> create(@RequestHeader("Authorization") String token, @PathVariable("album_id") String album_id, @RequestBody Star star) {
        return userService.findStarById(jwtUtil.getId(token)).flatMap(sum -> {
            star.setStarFrom(jwtUtil.getId(token));
            star.setStarAt(album_id);
            if (sum >= star.getNum() && !star.getStarTo().equals(star.getStarFrom())) {
                return starService.create(star).then(notificationService.save(new Notification(star.getStarTo(), star.getStarAt(), "albumBeStarred", "url"))).map(then -> ResponseEntity.ok(new Result<Star>("打赏成功")));
            }
            return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result<Star>("拥有star数不足or自己给自己打赏是不行的哦")));
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result<>("被打赏用户不存在")));
    }

    //按专辑获取打赏列表
    @GetMapping("/albums/{album_id}/star")
    public Mono<ResponseEntity<Result<List<Star>>>> get(@PathVariable("album_id") String album_id) {
        return albumService.findByIdAndStatusActive(album_id).flatMap(album -> {
                return starService.findAllByStarAt(album_id).collectList().map(list -> ResponseEntity.ok(new Result<>("拉取成功", list)));
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result<>("专辑不存在")));
    }
}
