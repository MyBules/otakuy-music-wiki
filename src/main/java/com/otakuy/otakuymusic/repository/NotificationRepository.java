package com.otakuy.otakuymusic.repository;

import com.otakuy.otakuymusic.model.Notification;
import org.springframework.data.mongodb.repository.ExistsQuery;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface NotificationRepository extends ReactiveMongoRepository<Notification, String> {
    Flux<Notification> findAllByIsReadAndOwner(boolean isRead, String owner);

    @ExistsQuery("{'owner': ?0 ,'isRead': flase }")
    Mono<Boolean> existByIsReadAndOwner(String user_id);
}
