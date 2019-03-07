package com.otakuy.otakuymusic.controller;

import com.otakuy.otakuymusic.model.Notification;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.service.NotificationService;
import com.otakuy.otakuymusic.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NotificationController {
    private final NotificationService notificationService;

    private final JWTUtil jwtUtil;

    //获取消息列表
    @GetMapping("/notifications")
    public Mono<ResponseEntity<Result<List<Notification>>>> getUnreadList(@RequestHeader("Authorization") String token, @RequestParam Boolean isRead) {
        return notificationService.findAllByIsReadAndOwner(isRead, jwtUtil.getId(token)).collectList().flatMap(list -> {
            if (!isRead)
                return notificationService.updateAllToIsRead(list).map(updateResult -> ResponseEntity.ok().body(new Result<>("拉取成功", list)));
            return Mono.just(ResponseEntity.ok().body(new Result<>("拉取成功", list)));
        });
    }

    //查看新消息条数
    @GetMapping("/notifications/noRead")
    public Mono<ResponseEntity<Result<Long>>> getNotificationsStatus(@RequestHeader("Authorization") String token) {
        return notificationService.countByIsReadAndOwner(jwtUtil.getId(token)).map(count -> {
            if (count != 0)
                return ResponseEntity.ok().body(new Result<>("有未读消息", count));
            return ResponseEntity.ok().body(new Result<>("无未读消息", count));
        });
    }

}
