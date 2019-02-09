package com.otakuy.otakuymusic.service;

import com.otakuy.otakuymusic.model.Album;
import com.otakuy.otakuymusic.model.Revision;
import com.otakuy.otakuymusic.repository.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.lang.reflect.InvocationTargetException;

@Service
public class RevisionService {
    private final AlbumRepository albumRepository;

    @Autowired
    public RevisionService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
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
}
