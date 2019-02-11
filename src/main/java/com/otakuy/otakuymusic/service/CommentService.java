package com.otakuy.otakuymusic.service;

import com.otakuy.otakuymusic.model.Comment;
import com.otakuy.otakuymusic.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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

    public Flux<Comment> findAllByAlbum_id(String album_id, Pageable pageable) {
        return commentRepository.findAllByAlbum(album_id, pageable);
    }

    public Mono<Comment> save(Comment comment) {
        return commentRepository.save(comment);
    }
}
