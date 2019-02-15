package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Comment;
import com.otakuy.otakuymusic.model.Notification;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.service.CommentService;
import com.otakuy.otakuymusic.service.NotificationService;
import com.otakuy.otakuymusic.service.UserService;
import com.otakuy.otakuymusic.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {
    private final CommentService commentService;
    private final NotificationService notificationService;
    private final UserService userService;
    private final JWTUtil jwtUtil;

    //拉取专辑下所有评论
    @GetMapping("/albums/{album_id}/comments")
    public Mono<ResponseEntity<Result<List<Comment>>>> pullAllCommentByAlbum_id(@PathVariable("album_id") String album_id, @RequestParam Integer page) {
        return commentService.findAllByAlbum_id(album_id, PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"))).collectList().map(comments -> ResponseEntity.ok(new Result<>("该专辑共有" + comments.size() + "条评论", comments)));
    }

    //提交评论
    @PostMapping("/albums/{album_id}/comments")
    public Mono<ResponseEntity<Result<Comment>>> pushComment(@RequestHeader("Authorization") String token, @Validated @RequestBody Comment comment) {
        return userService.findById(jwtUtil.getId(token)).flatMap(user -> {
            comment.init(user);
            return commentService.save(comment).flatMap(c -> notificationService.save(new Notification(comment.getPid(), comment.getAlbum(), "albumBeCommented", "url")).map(notification -> ResponseEntity.ok(new Result<>("评论提交成功", c))));
        });
    }
}
