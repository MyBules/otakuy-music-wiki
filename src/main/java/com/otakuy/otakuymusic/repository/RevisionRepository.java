package com.otakuy.otakuymusic.repository;

import com.otakuy.otakuymusic.model.Revision;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RevisionRepository extends ReactiveMongoRepository<Revision, String> {
}
