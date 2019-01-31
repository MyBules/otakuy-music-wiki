/*
 * Ardiansyah | http://ard.web.id
 *
 */
package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @author ardiansyah
 */
@RestController
public class ResourceREST {

    @GetMapping("/resource/user")
    @PreAuthorize("hasRole('USER')")
    public Mono<ResponseEntity<? extends Result>> user() {
        return Mono.just(ResponseEntity.ok(new Result<>("ok", "hello_world")));
    }

    @GetMapping("/resource/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public Mono<ResponseEntity<? extends Result>> admin() {
        return Mono.just(ResponseEntity.ok(new Result<>("ok", "hello_admin")));
    }

    @GetMapping("/resource/user-or-admin")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Mono<ResponseEntity<? extends Result>> userOrAdmin() {
        return Mono.just(ResponseEntity.ok(new Result<>("ok", "hello_user")));
    }
}
