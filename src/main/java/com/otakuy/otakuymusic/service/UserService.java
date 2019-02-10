package com.otakuy.otakuymusic.service;

import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.User;
import com.otakuy.otakuymusic.repository.UserRepository;
import com.otakuy.otakuymusic.util.UploadImageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    //按照用户名检索
    public Mono<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    //注册
    public Mono<User> userRegister(User user) {
        return userRepository.save(user);
    }

    //按照邮箱检索
    public Mono<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //按照用户名或邮箱检索
    public Flux<User> findByUsernameOrEmail(String username, String email) {
        return userRepository.findByUsernameOrEmail(username, email);
    }

    //上传(更新)头像
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

    public Mono<User> findById(String user_id) {
        return userRepository.findById(user_id);
    }
}
