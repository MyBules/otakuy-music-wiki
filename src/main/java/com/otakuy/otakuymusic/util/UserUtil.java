package com.otakuy.otakuymusic.util;

import com.otakuy.otakuymusic.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {
    //用户资料更新
    public User update(User oldUser, User user) {
        oldUser.setIntro(user.getIntro());
        return oldUser;
    }
}
