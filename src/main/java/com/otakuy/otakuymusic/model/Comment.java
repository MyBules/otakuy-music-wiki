package com.otakuy.otakuymusic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comment")
public class Comment {
    @NotBlank
    private String album;
    @NotBlank
    private String pid;//最上级pid为用户id
    @Id
    private String id;
    private String user_id;
    private String user_cover;
    private String user_username;
    @NotBlank
    private String content;
    private Date createTime;

    public void init(User user) {
        this.user_cover = user.getAvatar();
        this.user_id = user.getId();
        this.createTime = new Date();
        this.user_username = user.getUsername();
    }
}
