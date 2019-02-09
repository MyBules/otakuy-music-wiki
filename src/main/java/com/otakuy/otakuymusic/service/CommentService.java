package com.otakuy.otakuymusic.service;

import com.otakuy.otakuymusic.model.Comment;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CommentService {
    private final CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Flux<Comment> findAllByAlbum_id(String album_id) {
        return commentRepository.findAllByAlbum(album_id);
    }

    public Mono<Comment> save(Comment comment) {
        return commentRepository.save(comment);
    }
}
