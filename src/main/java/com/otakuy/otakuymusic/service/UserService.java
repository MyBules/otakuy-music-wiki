package com.otakuy.otakuymusic.service;

import com.otakuy.otakuymusic.exception.UnsupportedFormatException;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.User;
import com.otakuy.otakuymusic.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Mono<User> userRegister(User user) {
        return userRepository.save(user);
    }

    public Mono<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public Flux<User> findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email);
    }

    public String uploadAvatar(String user_id, FilePart filePart) throws IOException {
        String filename = filePart.filename();
        if (!filename.endsWith(".jpg") && !filename.endsWith(".png"))
            throw new UnsupportedFormatException(new Result<>(HttpStatus.BAD_REQUEST, "图片格式不支持,上传头像失败"));
        Path avatar = Paths.get("E:\\123\\" + user_id + ".png");
        if (!Files.exists(avatar))
            avatar = Files.createFile(avatar);
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(avatar, StandardOpenOption.WRITE);
        DataBufferUtils.write(filePart.content(), channel, 0)
                .doOnComplete(() -> {
                    userRepository.findById(user_id).flatMap(user -> {
                        user.setAvatar("https://avatar.otakuy.com/" + user_id + ".png");
                        return userRepository.save(user);
                    }).subscribe();
                    System.out.println("更新完成");
                })
                .subscribe();
        return "https://avatar.otakuy.com/" + user_id + ".png";
    }

}
