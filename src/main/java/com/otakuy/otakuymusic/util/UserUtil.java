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
        user.setAvatar("https://avatar.otakuy.com/default.png");
        user.setStar(0);
        return user;
    }
}
