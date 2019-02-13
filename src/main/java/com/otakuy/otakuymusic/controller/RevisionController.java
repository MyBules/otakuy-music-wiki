package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Album;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.Revision;
import com.otakuy.otakuymusic.service.AlbumService;
import com.otakuy.otakuymusic.service.RevisionService;
import com.otakuy.otakuymusic.service.UserService;
import com.otakuy.otakuymusic.util.AlbumUtil;
import com.otakuy.otakuymusic.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RevisionController {
    private final JWTUtil jwtUtil;
    private final AlbumUtil albumUtil;
    private final RevisionService revisionService;
    private final AlbumService albumService;
    private final UserService userService;

    //提交修改
    @PostMapping("/albums/{album_id}/revisions")
    public Mono<ResponseEntity<Result<String>>> create(@RequestHeader("Authorization") String token, @Validated @RequestBody Revision revision) {
        return albumService.existByIdAndStatusActive(revision.getAlbum()).flatMap(exist -> {
            if (!exist)
                return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result<>("专辑不存在或未审核通过")));
            revision.setCommitter(jwtUtil.getId(token));
            revision.setStatus("block");
            return revisionService.save(revision).map(then -> ResponseEntity.ok().body(new Result<String>("提交修改成功,等待维护者审核")));
        });

    }

    //查看专辑下所有等待修改队列
    @GetMapping("/albums/{album_id}/revisions")
    public Mono<ResponseEntity<Result<List<Revision>>>> pull(@RequestHeader("Authorization") String token, @PathVariable("album_id") String album_id) {
        return revisionService.findAllByAlbum(album_id).collectList().map(revisions -> ResponseEntity.ok().body(new Result<>("共" + revisions.size() + "等待审核的修改提交", revisions)));
    }

    //维护者应用修改
    @GetMapping("/albums/{album_id}/revisions/{revision_id}")
    public Mono<ResponseEntity<Result<Album>>> commit(@RequestHeader("Authorization") String token, @PathVariable("album_id") String album_id, @PathVariable("revision_id") String revision_id) {
        return albumService.findById(album_id).flatMap(album -> {
            albumUtil.checkAuthority(token, album);
            return revisionService.findByIdAndStatusBlock(revision_id).flatMap(revision -> {
                userService.updateStarById(revision.getCommitter(), 10).subscribe();//加分
                return revisionService.commitRevision(revision).map(newAlbum -> ResponseEntity.status(HttpStatus.OK).body(new Result<>("应用修改成功", newAlbum)));
            }).defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result<>("修改不存在")));
        }).defaultIfEmpty(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Result<>("专辑不存在")));
    }
}
