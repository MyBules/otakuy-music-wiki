package com.otakuy.otakuymusic.service;

import com.otakuy.otakuymusic.model.Notification;
import com.otakuy.otakuymusic.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public Flux<Notification> findAllByIsReadAndOwner(boolean isRead, String owner) {
        return notificationRepository.findAllByIsReadAndOwner(isRead, owner);
    }

    public Mono<Notification> save(Notification notification) {
        return notificationRepository.save(notification);
    }

    public Mono<Boolean> existByIsReadAndOwner(String user_id) {
        return notificationRepository.existByIsReadAndOwner(user_id);
    }
}
