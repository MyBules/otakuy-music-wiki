package com.otakuy.otakuymusic.repository;

import com.otakuy.otakuymusic.model.Album;
import org.springframework.data.mongodb.repository.DeleteQuery;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AlbumRepository extends ReactiveMongoRepository<Album, String> {
    Flux<Album> findAllByOwner(String owner);

    @Query("{'owner': ?0 ,'status': { '$ne' : \"reject\"}}")
    Flux<Album> findAllByOwnerAndStatusNotReject(String owner);

    Flux<Album> findAllByTitle(String title);

    @Query("{'title': ?0 ,'status': { '$ne' : \"reject\"}}")
    Flux<Album> findAllByTitleAndStatusNotReject(String title);

    Flux<Album> findAllByIsRecommend(Boolean isRecommend);

    @Query("{'tags.name': ?0 ,'status': { '$ne' : \"reject\"}}")
    Flux<Album> findAllByTagAndStatusNotReject(String tag);

    @Query("{'_id': ?0 ,'status': { '$ne' : \"reject\"}}")
    Mono<Album> findByIdAndStatusNotReject(String id);

    @Query("{'title': ?0 ,'status': { '$ne' : \"reject\"}}")
    Flux<Album> findByTitle(String title);
}
