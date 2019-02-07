package com.otakuy.otakuymusic.repository;

import com.otakuy.otakuymusic.model.Album;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface AlbumRepository extends ReactiveMongoRepository<Album, String> {

    Flux<Album> findAllByOwner(String owner);

    Flux<Album> findAllByTitle(String title);

    Flux<Album> findAllByIsRecommend(Boolean isRecommend);
}
