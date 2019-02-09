package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Album;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.Revision;
import com.otakuy.otakuymusic.service.AlbumService;
import com.otakuy.otakuymusic.service.RevisionService;
import com.otakuy.otakuymusic.util.AlbumUtil;
import com.otakuy.otakuymusic.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
public class RevisionController {
    private final JWTUtil jwtUtil;
    private final AlbumUtil albumUtil;
    private final RevisionService revisionService;
    private final AlbumService albumService;

    @Autowired
    public RevisionController(JWTUtil jwtUtil, AlbumUtil albumUtil, RevisionService revisionService, AlbumService albumService) {
        this.jwtUtil = jwtUtil;
        this.albumUtil = albumUtil;
        this.revisionService = revisionService;
        this.albumService = albumService;
    }

    @PostMapping("/albums/{album_id}/revisions")
    public Mono<ResponseEntity<Result<String>>> create(@RequestHeader("Authorization") String token, @RequestBody Revision revision) {
        revision.setCommitter(jwtUtil.getId(token));
        revisionService.save(revision);
        return Mono.just(ResponseEntity.ok().body(new Result<>("提交修改成功,等待维护者审核")));
    }

    @GetMapping("/albums/{album_id}/revisions")
    public Mono<ResponseEntity<Result<List<?>>>> pull(@RequestHeader("Authorization") String token, @RequestBody String album_id) {
        return revisionService.findAllByAlbum(album_id).collectList().map(revisions -> ResponseEntity.ok().body(new Result<>("共"+revisions.size()+"等待审核的修改提交",revisions)));
    }

    @GetMapping("/albums/{album_id}/revisions")
    public Mono<ResponseEntity<Result<Album>>> commit(@RequestHeader("Authorization") String token, @PathVariable String album_id, @RequestBody Revision revision) {
        return albumService.findById(album_id).flatMap(album -> {
            albumUtil.checkAuthority(token, album);
            return revisionService.commitRevision(revision).map(newAlbum-> ResponseEntity.status(HttpStatus.OK).body(new Result<>("应用修改成功",newAlbum)));
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result<>("专辑不存在")));
    }
}
