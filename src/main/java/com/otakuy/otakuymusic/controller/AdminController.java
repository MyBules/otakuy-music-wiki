package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Album;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.User;
import com.otakuy.otakuymusic.service.AlbumService;
import com.otakuy.otakuymusic.service.NotificationService;
import com.otakuy.otakuymusic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasRole('ADMIN')")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AdminController { //暂时只做专辑审核以及查看专辑,用户(用户的增删改查专辑的增删改查与重授权以后再说吧)
    private final AlbumService albumService;
    private final UserService userService;
    private final NotificationService notificationService;

    //修改专辑推荐状态
    @PutMapping("/albums/recommend")
    public Mono<ResponseEntity<Result<String>>> modifyIsRecommend(@RequestBody() Album[] albums, @RequestParam Boolean isRecommend) {
        return albumService.updateIsRecommend(Arrays.stream(albums).parallel().map(Album::getId).collect(Collectors.toList()), isRecommend).map(updateResult -> ResponseEntity.ok().body(new Result<>("修改专辑推荐属性成功")));
    }

    //专辑审核/拒绝
    @PutMapping("/albums/{status}")
    public Mono<ResponseEntity<Result<String>>> auditingAlbum(@RequestBody() Album[] albums, @PathVariable String status) {
        return albumService.updateStatus(Arrays.stream(albums).parallel().map(Album::getId).collect(Collectors.toList()), status).map(updateResult -> ResponseEntity.ok().body(new Result<>("修改专辑推荐属性成功")));
    }

    //拉取专辑(审核通过/没通过/待审核)
    @GetMapping("/albums")
    public Mono<ResponseEntity<Result<List<Album>>>> getAlbumList(@RequestParam String status, @RequestParam Integer page) {
        return albumService.findAllByStatus(status, PageRequest.of(page, 16, Sort.by(Sort.Direction.DESC, "id"))).collectList().map(albums -> ResponseEntity.ok().body(new Result<>("拉取专辑列表成功", albums)));
    }

    //拉取推荐专辑
    @GetMapping("/recommendAlbums")
    public Mono<ResponseEntity<Result<List<Album>>>> recommendAlbumList() {
        return albumService.findAllByIsRecommend().collectList().map(albums -> ResponseEntity.ok().body(new Result<>("拉取专辑列表成功", albums)));
    }

    //拉取专辑总数(审核通过/没通过/待审核)
    @GetMapping("/albumsCount")
    public Mono<ResponseEntity<Result<Long>>> getAlbumsCount(@RequestParam String status) {
        return albumService.countAllByStatus(status).map(sum -> ResponseEntity.ok().body(new Result<>("统计专辑数量成功", sum)));
    }

    //拉取推荐专辑总数
    @GetMapping("/recommendAlbumsCount")
    public Mono<ResponseEntity<Result<Long>>> getRecommendAlbumsCount() {
        return albumService.countAllByIsRecommend(true).map(sum -> ResponseEntity.ok().body(new Result<>("统计专辑数量成功", sum)));
    }

    //拉取用户(被block/未被block)
    @GetMapping("/users")
    public Mono<ResponseEntity<Result<List<User>>>> getUserList(@RequestParam Boolean isEnable, @RequestParam Integer page) {
        return userService.findAllByEnabled(isEnable, PageRequest.of(page, 16, Sort.by(Sort.Direction.DESC, "id"))).collectList().map(users -> ResponseEntity.ok().body(new Result<>("拉取用户列表成功", users)));
    }

    //拉取用户总数(被block/未被block)
    @GetMapping("/usersCount")
    public Mono<ResponseEntity<Result<Long>>> getUsersCount(@RequestParam Boolean isEnable) {
        return userService.countAllByEnabled(isEnable).map(sum -> ResponseEntity.ok().body(new Result<>("统计用户数量成功", sum)));
    }

    //拉取评论总数
    @GetMapping("/notificationCount")
    public Mono<ResponseEntity<Result<Long>>> getNotificationCount() {
        return notificationService.countAll().map(sum -> ResponseEntity.ok().body(new Result<>("统计回复数量成功", sum)));
    }

    //拉取专辑信息
    @GetMapping("/albums/{album_id}")
    public Mono<ResponseEntity<Result<Album>>> getAlbumDetail(@PathVariable("album_id") String album_id) {
        return albumService.findById(album_id).map(album -> ResponseEntity.status(HttpStatus.OK).body(new Result<>("拉取专辑详细成功", album))).defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result<>("专辑不存在")));
    }

}
