package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Notification;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.service.NotificationService;
import com.otakuy.otakuymusic.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationController {
    private final NotificationService notificationService;
    private final ReactiveMongoTemplate reactiveMongoTemplate;
    private final JWTUtil jwtUtil;

    //获取消息列表
    @GetMapping("/notifications")
    public Mono<ResponseEntity<Result<List<Notification>>>> getUnreadList(@RequestHeader("Authorization") String token, @RequestParam Boolean isRead) {
        return notificationService.findAllByIsReadAndOwner(isRead, jwtUtil.getId(token)).collectList().map(list -> {
            if (!isRead)
                reactiveMongoTemplate.updateFirst(new Query(where("_id").in(list.stream().parallel().map(Notification::getId).collect(Collectors.toList()))),
                        new Update().set("isRead", true), Notification.class).subscribe();
            return ResponseEntity.ok().body(new Result<>("拉取成功", list));
        });
    }

    //是否有新消息
    @GetMapping("/notifications/read")
    public Mono<ResponseEntity<Result<Boolean>>> getNotificationsStatus(@RequestHeader("Authorization") String token) {
        return notificationService.existByIsReadAndOwner(jwtUtil.getId(token)).map(exist -> {
            if (exist)
                return ResponseEntity.ok().body(new Result<>("有未读消息", true));
            return ResponseEntity.ok().body(new Result<>("无未读消息", false));
        });
    }

}
