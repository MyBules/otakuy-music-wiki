package com.otakuy.otakuymusic.util;

import com.otakuy.otakuymusic.model.User;
import com.otakuy.otakuymusic.model.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserUtil {
    private final PBKDF2Encoder passwordEncoder;

    //用户资料更新
    public User update(User oldUser, String intro) {
        oldUser.setIntro(intro);
        return oldUser;
    }

    //用户资料初始化
    public User init(User user) {
        user.setId(null);
        user.setEnabled(true);
        user.setRole(Arrays.asList(Role.ROLE_USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAvatar("https://ws4.sinaimg.cn/large/006346uDgy1g0z824nq5tj30i60i7gli.jpg");
        user.setStar(100);
        return user;
    }
}
