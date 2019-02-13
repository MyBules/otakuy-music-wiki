package com.otakuy.otakuymusic.repository;

import com.otakuy.otakuymusic.model.Star;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface StarRepository extends ReactiveMongoRepository<Star, String> {
    Flux<Star> findAllByStarAt(String album_id);
}
