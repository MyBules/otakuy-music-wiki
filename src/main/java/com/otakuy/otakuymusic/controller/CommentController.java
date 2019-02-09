package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Comment;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class CommentController {
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //拉取专辑下所有评论
    @GetMapping("/albums/{album_id}/comments")
    public Mono<ResponseEntity<Result<List<Comment>>>> pullAllCommentByAlbum_id(@PathVariable("album_id") String album_id) {
        return commentService.findAllByAlbum_id(album_id).collectList().map(comments -> ResponseEntity.ok(new Result<>("该专辑共有" + comments.size() + "条评论", comments)));
    }

    //提交评论
    @PostMapping("/albums/{album_id}/comments")
    public Mono<ResponseEntity<Result<Comment>>> pushComment(@RequestBody Comment comment) {
        return commentService.save(comment).map(c -> ResponseEntity.ok(new Result<>("评论提交成功", c)));
    }
}
