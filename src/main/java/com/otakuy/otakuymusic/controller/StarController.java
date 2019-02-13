package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.Star;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class StarController {
    //打赏
    @PostMapping("/album/{album_id}/star")
    public Mono<ResponseEntity<Result<String>>> create(@RequestHeader("Authorization") String token, @PathVariable("album_id") String album_id, @RequestBody Star star) {
        return null;
    }
    //按专辑获取打赏列表
}
