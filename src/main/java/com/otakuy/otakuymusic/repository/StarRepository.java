package com.otakuy.otakuymusic.repository;

import com.otakuy.otakuymusic.model.Star;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StarRepository extends ReactiveMongoRepository<Star, String> {
}
