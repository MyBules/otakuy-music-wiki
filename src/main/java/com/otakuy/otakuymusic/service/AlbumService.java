package com.otakuy.otakuymusic.service;

import com.otakuy.otakuymusic.model.Album;
import com.otakuy.otakuymusic.model.Revision;
import com.otakuy.otakuymusic.model.douban.AlbumSuggestion;
import com.otakuy.otakuymusic.repository.AlbumRepository;
import com.otakuy.otakuymusic.util.DoubanApi.DoubanUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    @Autowired
    public AlbumService(AlbumRepository albumRepository, ReactiveMongoTemplate reactiveMongoTemplate, DoubanUtil doubanUtil) {
        this.albumRepository = albumRepository;
        this.reactiveMongoTemplate = reactiveMongoTemplate;
        this.doubanUtil = doubanUtil;
    }

    public Flux<Album> findAllByOwner(String owner) {
        return albumRepository.findAllByOwner(owner);
    }

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

    public Mono<Album> update(Album album) {
        return albumRepository.save(album);
    }

    public Mono<Album> add(Album album) {
        return albumRepository.save(album);
    }

/*    public Flux<Album> findAllByTitle(String title) {
        Pattern pattern = Pattern.compile("^.*" + title + ".*$", Pattern.CASE_INSENSITIVE);
        Query query = Query.query(Criteria.where("title").regex(pattern));
        return reactiveMongoTemplate.find(query, Album.class, "album");
    }*/

    public Flux<Album> findAllByFilter(String filter, String param) {
        Pattern pattern = Pattern.compile("^.*" + param + ".*$", Pattern.CASE_INSENSITIVE);
        Query query = Query.query(Criteria.where(filter).regex(pattern));
        return reactiveMongoTemplate.find(query, Album.class, "album");
    }

    public List<AlbumSuggestion> getAlbumSuggestionByDouban(String title) {
        return doubanUtil.getAlbumSuggestion(title);
    }

    public Album getAlbumDetailByDouban(String douban_id) throws IOException {
        //  return doubanUtil.getAlbumSuggestion(douban_id);
        return doubanUtil.getAlbumDetail(douban_id);
    }
}
