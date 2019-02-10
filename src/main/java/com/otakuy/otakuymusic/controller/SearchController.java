package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Album;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.User;
import com.otakuy.otakuymusic.service.AlbumService;
import com.otakuy.otakuymusic.service.UserService;
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
public class SearchController {
    private final UserService userService;
    private final AlbumService albumService;

    @Autowired
    public SearchController(UserService userService, AlbumService albumService) {
        this.userService = userService;
        this.albumService = albumService;
    }

    //根据指定标题搜索专辑
    @GetMapping("/byTitle")
    public Mono<ResponseEntity<Result<List<Album>>>> findAllByTitleAndStatusNotReject(@RequestParam("title") String title, @RequestParam Integer offset, @RequestParam Integer limit) {
        return albumService.findAllByTitleAndStatusNotReject(title).collectList().map(albums -> ResponseEntity.ok(new Result<>("共有" + albums.size() + "", albums)));
    }

    //按照指定tag检索专辑
    @GetMapping("/byTag")
    public Mono<ResponseEntity<Result<List<Album>>>> findAllByTagAndStatusNotReject(@RequestParam("tag") String tag/*,@RequestParam Integer offset, @RequestParam Integer limit*/) {
        return albumService.findAllByTagAndStatusNotReject(tag).collectList().map(albums -> ResponseEntity.ok(new Result<>("共有" + albums.size() + "", albums)));
    }

    //根据指定用户名模糊搜索用户
    @GetMapping("/users")
    public Mono<ResponseEntity<Result<List<User>>>> findByUsername(@RequestParam String username/*,@RequestParam Integer offset, @RequestParam Integer limit*/) {
        //return albumService.findAllByFilter(filter, param).collectList().map(albums -> ResponseEntity.ok(new Result<>("共有" + albums.size() + "", albums)));
        return null;
    }
}
