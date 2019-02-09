package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.Revision;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/revisions")
public class RevisionController {
    @PostMapping("")
    public Mono<ResponseEntity<Result<String>>> create(@RequestHeader("Authorization") String token, @RequestBody Revision revision) {
        return null;
    }
    @GetMapping("")
    public Mono<ResponseEntity<Result<String>>> get(@RequestHeader("Authorization") String token, @RequestBody Revision revision) {
        return null;
    }
    @DeleteMapping("")
    public Mono<ResponseEntity<Result<String>>> delete(@RequestHeader("Authorization") String token, @RequestBody Revision revision) {
        return null;
    }


}
