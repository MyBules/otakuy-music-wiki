package com.otakuy.otakuymusic.repository;

import com.otakuy.otakuymusic.model.Revision;
import org.springframework.data.mongodb.repository.CountQuery;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface RevisionRepository extends ReactiveMongoRepository<Revision, String> {
   @CountQuery(value = "{'album':  ?0, 'modificationPoint': ?1,'status': 'block'}")
    Mono<Integer> countAllByAlbumAndModificationPoint(String album_id, String modification_point);
    @CountQuery(value = "{'album':  ?0, 'status': 'block'}")
    Flux<Revision> findAllByAlbum(String album_id);
}
