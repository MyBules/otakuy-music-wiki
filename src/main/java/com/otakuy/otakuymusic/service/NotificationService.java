package com.otakuy.otakuymusic.service;

import com.mongodb.client.result.UpdateResult;
import com.otakuy.otakuymusic.model.Notification;
import com.otakuy.otakuymusic.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    public Flux<Notification> findAllByIsReadAndOwner(boolean isRead, String owner) {
        return notificationRepository.findAllByIsReadAndOwnerOrderByCreateTimeAsc(isRead, owner, new Sort(Sort.Direction.DESC, "_id"));
    }

    public Mono<Notification> save(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Mono<Long> countByIsReadAndOwner(String user_id) {
        return notificationRepository.countByIsReadAndOwner(user_id);
    }

    public Mono<UpdateResult> updateAllToIsRead(List<Notification> notifications) {
        return reactiveMongoTemplate.updateMulti(new Query(where("_id").in(notifications.stream().parallel().map(Notification::getId).collect(Collectors.toList()))),
                new Update().set("isRead", true), Notification.class);
    }
}
