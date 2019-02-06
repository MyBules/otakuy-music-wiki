package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Album;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.Revision;
import com.otakuy.otakuymusic.model.douban.AlbumSuggestion;
import com.otakuy.otakuymusic.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.List;

@RestController
public class AlbumController {
    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("/uers/{owner}/albums")
    public Mono<ResponseEntity<Result<List<Album>>>> findAllByOwner(@PathVariable("owner") String owner/*, @RequestParam Integer offset, @RequestParam Integer limit*/) {
        return albumService.findAllByOwner(owner).collectList().map(albums -> ResponseEntity.ok(new Result<>("共有" + albums.size() + "", albums)));
    }

    /*    @GetMapping("/search/tags")
        public Mono<ResponseEntity<Result<List<Album>>>> findByTag(@RequestParam String tag*//*,@RequestParam Integer offset, @RequestParam Integer limit*//*) {
        return albumService.findAllByTitle(tag).collectList().map(albums -> ResponseEntity.ok(new Result<>("共有" + albums.size() + "", albums)));
    }*/
    @GetMapping("/douban")
    public Mono<ResponseEntity<Result<List<AlbumSuggestion>>>> getAlbumSuggestionByDouban(@RequestParam String title) {
        return Mono.just(ResponseEntity.ok(new Result<>("ok", albumService.getAlbumSuggestionByDouban(title))));
    }

    @GetMapping("/douban/{douban_id}")
    public Mono<ResponseEntity<Result<Album>>> getAlbumDetailByDouban(@PathVariable("douban_id") String douban_id) throws IOException {
        return Mono.just(ResponseEntity.ok(new Result<>("ok", albumService.getAlbumDetailByDouban(douban_id))));
    }

    @PostMapping("/test")
    public Mono<ResponseEntity<Result<Album>>> test(@RequestBody Revision revision) {
        return albumService.modify(revision).map(album -> ResponseEntity.ok(new Result<>(null, album)));
    }
    @PostMapping(value = "/albums/{album_id}/covers", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<ResponseEntity<Result<String>>> uploadCover(@PathVariable("album_id") String album_id,@RequestPart("file") FilePart filePart) throws IOException {
        return Mono.just(ResponseEntity.ok(new Result<>("上传专辑封面成功", albumService.uploadCover(album_id,filePart))));
    }
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
