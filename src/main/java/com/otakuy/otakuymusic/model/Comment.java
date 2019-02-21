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
    private String to_id;//最上级pid为用户id
    @NotBlank
    private String to_username;//最上级pid为用户id
    @Id
    private String id;
    @NotBlank
    private String from_id;
    @NotBlank
    private String from_username;
    @NotBlank
    private String content;
    private Date createTime;

    public void init(User user) {
        this.from_id = user.getId();
        this.createTime = new Date();
        this.from_username = user.getUsername();
    }
}
