package com.otakuy.otakuymusic.service;

import com.otakuy.otakuymusic.exception.AuthorityException;
import com.otakuy.otakuymusic.model.Album;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.Revision;
import com.otakuy.otakuymusic.model.douban.AlbumSuggestion;
import com.otakuy.otakuymusic.repository.AlbumRepository;
import com.otakuy.otakuymusic.util.DoubanApi.DoubanUtil;
import com.otakuy.otakuymusic.util.JWTUtil;
import com.otakuy.otakuymusic.util.UploadImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class AlbumService {
    private final AlbumRepository albumRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private final DoubanUtil doubanUtil;
    private final JWTUtil jwtUtil;
    private final UploadImageUtil uploadImageUtil;

    @Autowired
    public AlbumService(AlbumRepository albumRepository, ReactiveMongoTemplate reactiveMongoTemplate, DoubanUtil doubanUtil, JWTUtil jwtUtil, UploadImageUtil uploadImageUtil) {
        this.albumRepository = albumRepository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
        this.doubanUtil = doubanUtil;
        this.jwtUtil = jwtUtil;
        this.uploadImageUtil = uploadImageUtil;
    }

    public Flux<Album> findAllByOwner(String owner) {
        return albumRepository.findAllByOwner(owner);
    }

    //维护者应用协助维护者发起的更改
    public Mono<Album> modify(Revision revision) {
        return albumRepository.findById(revision.getAlbum_id()).flatMap(album -> {
            try {
                album.getClass().getMethod((String) Revision.MODIFICATION_POINT_MAP.get(revision.getModification_point()), revision.getContent().getClass()).invoke(album, revision.getContent());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return albumRepository.save(album);
        });
    }

    //维护者更新专辑
    public Mono<Album> update(Album album) {
        return albumRepository.findById(album.getId()).flatMap(oldAlbum -> {
            if (oldAlbum.getStatus().equals("reject"))
                oldAlbum.setStatus("block");
            oldAlbum.setMusic163Id(album.getMusic163Id());
            oldAlbum.setTitle(album.getTitle());
            oldAlbum.setTracks(album.getTracks());
            oldAlbum.setArtists(album.getArtists());
            oldAlbum.setPubdate(album.getPubdate());
            oldAlbum.setPublisher(album.getPublisher());
            oldAlbum.setGenres(album.getGenres());
            oldAlbum.setVersion(album.getVersion());
            oldAlbum.setTags(album.getTags());
            oldAlbum.setIntro(album.getIntro());
            oldAlbum.setCover(album.getCover());
            oldAlbum.setDouban_url(album.getDouban_url());
            oldAlbum.setCode(album.getCode());
            oldAlbum.setDownloadRes(album.getDownloadRes());
            return albumRepository.save(oldAlbum);
        });
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

    //按照条件筛选
    public Flux<Album> findAllByFilter(String filter, String param) {
        Pattern pattern = Pattern.compile("^.*" + param + ".*$", Pattern.CASE_INSENSITIVE);
        Query query = Query.query(Criteria.where(filter).regex(pattern));
        return reactiveMongoTemplate.find(query, Album.class, "album");
    }

    //获取专辑建议
    public List<AlbumSuggestion> getAlbumSuggestionByDouban(String title) {
        return doubanUtil.getAlbumSuggestion(title);
    }

    //从豆瓣获取专辑详细信息
    public Album getAlbumDetailByDouban(String douban_id) throws IOException {
        //  return doubanUtil.getAlbumSuggestion(douban_id);
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

    //验证是否有查看下载资源资格
    public void checkPermission(String token, Album album) {
        Integer star = jwtUtil.getStar(token);
        if (star - album.getDownloadRes().getPermission() < 0)
            throw new AuthorityException((new Result<>(HttpStatus.UNAUTHORIZED, "权限不足")));
    }

    //验证是否有查看下载资源资格
    public void checkPermission(String token, String album_id) {
        Integer star = jwtUtil.getStar(token);
        albumRepository.findById(album_id).subscribe(album -> {
            if (star - album.getDownloadRes().getPermission() < 0)
                throw new AuthorityException((new Result<>(HttpStatus.UNAUTHORIZED, "权限不足")));
        });
    }

    //验证专辑所有权
    public Boolean checkAuthority(String token, String album_id) {
        String id = jwtUtil.getId(token);
        albumRepository.findById(album_id).subscribe(album -> {
            if (!album.getOwner().equals(jwtUtil.getId(token)))
                throw new AuthorityException((new Result<>(HttpStatus.UNAUTHORIZED, "权限不足")));
        });
        return true;
    }

    //验证专辑所有权
    public Boolean checkAuthority(String token, Album album) {
        String id = jwtUtil.getId(token);
        if (!album.getOwner().equals(jwtUtil.getId(token)))
            throw new AuthorityException((new Result<>(HttpStatus.UNAUTHORIZED, "权限不足")));
        return true;
    }

    //新增专辑初始化
    public Album initNew(String token, Album album) {
        album.setOwner(jwtUtil.getId(token));
        album.setRating_count(0);
        album.setRating(0F);
        album.setStatus("block");
        album.setIsRecommend(false);
        return album;
    }

    //删除专辑
    public Mono<Void> delete(Album album) {
        return albumRepository.delete(album);
    }

    //创建新的专辑
    public Mono<Album> create(Album album) {
        return albumRepository.save(album);
    }

    //按照id查找专辑
    public Mono<Album> findById(String album_id) {
        return albumRepository.findById(album_id);
    }
}
