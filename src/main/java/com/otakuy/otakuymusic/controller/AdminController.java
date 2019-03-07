package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Album;
import com.otakuy.otakuymusic.model.Notification;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.User;
import com.otakuy.otakuymusic.service.AlbumService;
import com.otakuy.otakuymusic.service.NotificationService;
import com.otakuy.otakuymusic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    @PostMapping("/albums/recommend")
    public Mono<ResponseEntity<Result<String>>> modifyIsRecommend(@RequestBody() Album[] albums, @RequestParam Boolean isRecommend) {
        return albumService.updateIsRecommend(Arrays.stream(albums).parallel().map(Album::getId).collect(Collectors.toList()), isRecommend).map(updateResult -> ResponseEntity.ok().body(new Result<>("修改专辑推荐属性成功")));
    }

    //专辑审核
    @GetMapping("/albums/{album_id}/auditing")
    public Mono<ResponseEntity<Result<String>>> auditingAlbum(@PathVariable("album_id") String album_id, @RequestParam String status) {
        return albumService.findByIdAndStatusNotReject(album_id).flatMap(album -> {
            album.setStatus(status);
            return albumService.save(album).then(notificationService.save(new Notification(album.getOwner(), album.getId(), "albumBe" + status.substring(0, 1).toUpperCase() + status.substring(1)))).map(a -> ResponseEntity.ok().body(new Result<>("专辑审核成功")));
        });
    }

    //拉取专辑(审核通过/没通过/待审核)
    @GetMapping("/albums")
    public Mono<ResponseEntity<Result<List<Album>>>> getAlbumList(@RequestParam String status, @RequestParam Integer page) {
        return albumService.findAllByStatus(status, PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "id"))).collectList().map(albums -> ResponseEntity.ok().body(new Result<>("拉取专辑列表成功", albums)));
    }

    //拉取用户(被block/未被block)
    @GetMapping("/users")
    public Mono<ResponseEntity<Result<List<User>>>> getUserList(@RequestParam Boolean isEnable, @RequestParam Integer page) {
        return userService.findAllByEnabled(isEnable, PageRequest.of(page, 15, Sort.by(Sort.Direction.DESC, "id"))).collectList().map(users -> ResponseEntity.ok().body(new Result<>("拉取用户列表成功", users)));
    }
}
