package com.otakuy.otakuymusic.service;

import com.otakuy.otakuymusic.model.User;
import com.otakuy.otakuymusic.repository.UserRepository;
import com.otakuy.otakuymusic.util.PBKDF2Encoder;
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
    private final PBKDF2Encoder pbkdf2Encoder;

    @Autowired
    public UserService(UserRepository userRepository, UploadImageUtil uploadImageUtil, PBKDF2Encoder pbkdf2Encoder) {
        this.userRepository = userRepository;
        this.uploadImageUtil = uploadImageUtil;
        this.pbkdf2Encoder = pbkdf2Encoder;
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

    public Mono<User> updatePersonalInformation(User user) {
        return userRepository.save(user);
    }

    //修改密码
    public Mono<User> modifyPassword(String email, String password) {
        return findByEmail(email).flatMap(user -> {
            user.setPassword(pbkdf2Encoder.encode(password));
            System.out.println(user.getPassword());
            return userRepository.save(user);
        });
    }

    //查询star数
    public Mono<Integer> findStarById(String id) {
        return userRepository.findStarById(id).map(User::getStar);
    }
}
