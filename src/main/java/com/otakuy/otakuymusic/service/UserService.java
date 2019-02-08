package com.otakuy.otakuymusic.service;

import com.otakuy.otakuymusic.model.User;
import com.otakuy.otakuymusic.repository.UserRepository;
import com.otakuy.otakuymusic.util.UploadImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UploadImageUtil uploadImageUtil;

    @Autowired
    public UserService(UserRepository userRepository, UploadImageUtil uploadImageUtil) {
        this.userRepository = userRepository;
        this.uploadImageUtil = uploadImageUtil;
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
        uploadImageUtil.uploadImage(filePart, "E:\\123\\" + user_id + ".png", () -> {
            userRepository.findById(user_id).flatMap(user -> {
                user.setAvatar("https://img.otakuy.com/" + user_id + ".png");
                return userRepository.save(user);
            }).subscribe();
            System.out.println("更新完成");
        });
        return "https://img.otakuy.com/" + user_id + ".png";
    }

}
