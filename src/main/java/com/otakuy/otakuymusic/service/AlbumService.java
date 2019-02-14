package com.otakuy.otakuymusic.service;

import com.otakuy.otakuymusic.exception.CheckException;
import com.otakuy.otakuymusic.model.Album;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.douban.AlbumSuggestion;
import com.otakuy.otakuymusic.repository.AlbumRepository;
import com.otakuy.otakuymusic.util.AlbumUtil;
import com.otakuy.otakuymusic.util.DoubanApi.DoubanUtil;
import com.otakuy.otakuymusic.util.JWTUtil;
import com.otakuy.otakuymusic.util.UploadImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final UserService userService;
    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private final DoubanUtil doubanUtil;
    private final UploadImageUtil uploadImageUtil;
    private final JWTUtil jwtUtil;
    private final AlbumUtil albumUtil;

    public Flux<Album> findAllByOwner(String owner, Pageable pageable) {
        return albumRepository.findAllByOwner(owner, pageable);
    }

    public Flux<Album> findAllByOwnerAndStatusNotReject(String owner, Pageable pageable) {
        return albumRepository.findAllByOwnerAndStatusNotReject(owner, pageable);
    }

    //添加专辑
    public Mono<Album> add(Album album) {
        return albumRepository.save(album);
    }

    /*    public Flux<Album> findAllByTitle(String title) {
            Pattern pattern = Pattern.compile("^.*" + title + ".*$", Pattern.CASE_INSENSITIVE);
            Query query = Query.query(Criteria.where("title").regex(pattern));
            return reactiveMongoTemplate.find(query, Album.class, "album");
        }*/

/*    //按照条件查找
    public Flux<Album> findAllByFilter(String filter, String param) {
        Pattern pattern = Pattern.compile("^.*" + param + ".*$", Pattern.CASE_INSENSITIVE);
        Query query = Query.query(Criteria.where(filter).regex(pattern));
        return reactiveMongoTemplate.find(query, Album.class, "album").filter(album -> !album.getStatus().equals("reject"));
    }*/

    //获取专辑建议
    public List<AlbumSuggestion> getAlbumSuggestionByDouban(String title) throws UnsupportedEncodingException {
        return doubanUtil.getAlbumSuggestion(title);
    }

    //从豆瓣获取专辑详细信息
    public Album getAlbumDetailByDouban(String douban_id) throws IOException {
        return doubanUtil.getAlbumDetail(douban_id);
    }

    //上传(更新)专辑封面
    public String uploadCover(String album_id, FilePart filePart) throws IOException {
        uploadImageUtil.uploadImage(filePart, "E:\\123\\" + album_id + ".png", () -> {
            albumRepository.findById(album_id).flatMap(album -> {
                album.setCover("https://img.otakuy.com/" + album_id + ".png");
                return albumRepository.save(album);
            }).subscribe();
            System.out.println("更新完成");
        });
        return "https://img.otakuy.com/" + album_id + ".png";
    }

    //获取首页展示专辑
    public Flux<Album> findAllByIsRecommend() {
        return albumRepository.findAllByIsRecommend(true);
    }

    //保存专辑
    public Mono<Album> save(Album album) {
        return albumRepository.save(album);
    }

    //删除专辑
    public Mono<Void> delete(Album album) {
        return albumRepository.delete(album);
    }

    //创建新的专辑
    public Mono<Album> create(Album album) {
        return albumRepository.findAllByTitleAndStatusNotReject(album.getTitle()).hasElements().flatMap(exit -> {
            if (exit)
                throw new CheckException(new Result<>(HttpStatus.BAD_REQUEST, "重复专辑名"));
            return albumRepository.save(album);
        });
    }

    //按照id查找专辑
    public Mono<Album> findById(String album_id) {
        return albumRepository.findById(album_id);
    }

    //按照id查找专辑且状态为审核通过
    public Mono<Album> findByIdAndStatusActive(String album_id) {
        return albumRepository.findByIdAndStatusActive(album_id);
    }

    public Mono<Album> findByIdAndStatusNotReject(String album_id) {
        return albumRepository.findByIdAndStatusNotReject(album_id);
    }

    public Flux<Album> findAllByTagAndStatusActive(String tag, Pageable pageable) {
        return albumRepository.findAllByTagAndStatusActive(tag, pageable);
    }

    public Flux<Album> findAllByTitleAndStatusActive(String title, Pageable pageable) {
        return albumRepository.findAllByTitleAndStatusActive(title, pageable);
    }

    public Flux<Album> findAllByTitleAndStatusNotReject(String title) {
        return albumRepository.findAllByTitleAndStatusNotReject(title);
    }

    //验证是否有查看下载资源资格
    public Mono<Boolean> checkPermission(String token, Album album) {
        return userService.findStarById(jwtUtil.getId(token)).map(star -> star - album.getDownloadRes().getPermission() >= 0 || albumUtil.checkAuthorityWithoutThrowException(token, album));
    }
}
