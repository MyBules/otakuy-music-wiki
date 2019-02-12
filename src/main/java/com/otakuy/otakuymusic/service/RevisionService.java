package com.otakuy.otakuymusic.service;

import com.otakuy.otakuymusic.exception.RevisionQueueFullException;
import com.otakuy.otakuymusic.model.Album;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.Revision;
import com.otakuy.otakuymusic.repository.AlbumRepository;
import com.otakuy.otakuymusic.repository.RevisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.reflect.InvocationTargetException;

@Service
public class RevisionService {
    private final AlbumRepository albumRepository;
    private final RevisionRepository revisionRepository;

    @Autowired
    public RevisionService(AlbumRepository albumRepository, RevisionRepository revisionRepository) {
        this.albumRepository = albumRepository;
        this.revisionRepository = revisionRepository;
    }

    //维护者应用协助者发起的更改
    public Mono<Album> commitRevision(Revision revision) {
        return albumRepository.findById(revision.getAlbum()).flatMap(album -> {
            try {
                album.getClass().getMethod((String) Revision.MODIFICATION_POINT_MAP.get(revision.getModificationPoint()), revision.getContent().getClass()).invoke(album, revision.getContent());
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            return albumRepository.save(album);
        });
    }

    //检查修改队列
    private Mono<Boolean> checkRevisionQueue(Revision revision) {
        return revisionRepository.countAllByAlbumAndModificationPoint(revision.getAlbum(), revision.getModificationPoint()).map(count -> {
            if (count > 2)
                return false;
            return true;
        });
    }

    //保存修改请求到数据库
    public Mono save(Revision revision) {
        return checkRevisionQueue(revision).map(result -> {
            if (result)
                return revisionRepository.save(revision).subscribe();
            throw new RevisionQueueFullException(new Result<>(HttpStatus.BAD_REQUEST, "等待修改队列已满"));
        });
    }

    public Flux<Revision> findAllByAlbum(String album_id) {
        return revisionRepository.findAllByAlbum(album_id);
    }

/*    public Mono<Revision> findById(String revision_id) {

    }*/

    public Mono<Revision> findByIdAndStatusBlock(String revision_id) {
        return revisionRepository.findByIdAndStatusBlock(revision_id);
    }
}
