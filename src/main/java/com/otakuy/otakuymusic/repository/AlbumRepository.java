package com.otakuy.otakuymusic.repository;

import com.otakuy.otakuymusic.model.Album;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface AlbumRepository extends ReactiveMongoRepository<Album, String> {
    @Query(value = "{'owner': ?0}", fields = "{'title' : 1,'intro' : 1,'cover' : 1}")
    Flux<Album> findAllByOwner(String owner, Pageable pageable);

    @Query("{'owner': ?0 ,'status': { '$ne' : \"reject\"}}")
    Flux<Album> findAllByOwnerAndStatusNotReject(String owner, Pageable pageable);

    @Query("{'owner': ?0 ,'status': \"active\"}")
    Flux<Album> findAllByOwnerAndStatusActive(String owner, Pageable pageable);

    Flux<Album> findAllByTitle(String title, Pageable pageable);

    @Query("{'title': ?0 ,'status': { '$ne' : \"reject\"}}")
    Flux<Album> findAllByTitleAndStatusNotReject(String title);

    @Query(value = "{'title': {$regex:?0 ,$options:'i'} ,'status': \"active\"}", fields = "{'title' : 1,'intro' : 1,'cover' : 1}")
    Flux<Album> findAllByTitleAndStatusActive(String title, Pageable pageable);

    @Query(value = "{'tags.name': ?0 ,'status': \"active\"}", fields = "{'title' : 1,'intro' : 1,'cover' : 1}")
    Flux<Album> findAllByTagAndStatusActive(String tag, Pageable pageable);

    @Query("{'_id': ?0 ,'status': { '$ne' : \"reject\"}}")
    Mono<Album> findByIdAndStatusNotReject(String id);

    @Query(value = "{'_id': ?0 , 'status' :\"active\" }", fields = "{'owner' : 1}")
    Mono<Album> findByIdAndStatusActive(String id);

    @Query(value = "{'isRecommend': ?0}", fields = "{'title' : 1,'intro' : 1,'cover' : 1}")
    Flux<Album> findAllByIsRecommend(Boolean isRecommend);

    Flux<Album> findAllByStatus(String status, Pageable pageable);
}
