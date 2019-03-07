package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.User;
import com.otakuy.otakuymusic.service.AlbumService;
import com.otakuy.otakuymusic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/search")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SearchController {
    private final UserService userService;
    private final AlbumService albumService;
/*
    //根据指定标题模糊搜索专辑
    @GetMapping("/byTitle")
    public Mono<ResponseEntity<Result<List<Album>>>> findAllByTitleAndStatusNotReject(@RequestParam String title, @RequestParam Integer page) {
        return albumService.findAllByTitleAndStatusActive(title, PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"))).collectList().map(albums -> ResponseEntity.ok(new Result<>("共有" + albums.size() + "张专辑", albums)));
    }

    //按照指定tag检索专辑
    @GetMapping("/byTag")
    public Mono<ResponseEntity<Result<List<Album>>>> findAllByTagAndStatusNotReject(@RequestParam String tag, @RequestParam Integer page) {
        return albumService.findAllByTagAndStatusActive(tag, PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"))).collectList().map(albums -> ResponseEntity.ok(new Result<>("共有" + albums.size() + "张专辑", albums)));
    }*/

    //根据指定用户名模糊搜索用户
    @GetMapping("/users")
    public Mono<ResponseEntity<Result<List<User>>>> findByUsername(@RequestParam String username/*,@RequestParam Integer offset, @RequestParam Integer limit*/) {
        //return albumService.findAllByFilter(filter, param).collectList().map(albums -> ResponseEntity.ok(new Result<>("共有" + albums.size() + "", albums)));
        return null;
    }
}
