package com.otakuy.otakuymusic.service;

import com.mongodb.client.result.UpdateResult;
import com.otakuy.otakuymusic.model.User;
import com.otakuy.otakuymusic.repository.UserRepository;
import com.otakuy.otakuymusic.util.PBKDF2Encoder;
import com.otakuy.otakuymusic.util.UploadImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;
    private final UploadImageUtil uploadImageUtil;
    private final PBKDF2Encoder pbkdf2Encoder;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

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
        uploadImageUtil.uploadImage(filePart, "/home/www/avatar.otakuy.com/" + user_id + ".png", () -> {
            userRepository.findById(user_id).flatMap(user -> {
                user.setAvatar("https://avatar.otakuy.com/" + user_id + ".png");
                return userRepository.save(user);
            }).subscribe();
            System.out.println("更新完成");
        });
        return "https://avatar.otakuy.com/" + user_id + ".png";
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

    //更新star
    public Mono<UpdateResult> updateStarById(String id, Integer star) {
        return reactiveMongoTemplate.updateFirst(new Query(where("_id").is(id)),
                new Update().inc("star", star), User.class);
    }

    public Flux<User> findAllByEnabled(Boolean isEnable, Pageable pageable) {
        return userRepository.findAllByEnabled(isEnable, pageable);
    }

    public Mono<User> findAvatarById(String id) {
        return userRepository.findAvatarById(id);
    }

    public Mono<Long> countAllByEnabled(Boolean isEnable) {
        return userRepository.countAllByEnabled(isEnable);
    }
}
