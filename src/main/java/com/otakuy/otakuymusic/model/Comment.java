package com.otakuy.otakuymusic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

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
    @NotBlank
    private String user_id;
    @NotBlank
    private String user_cover;
    @NotBlank
    private String user_username;
    @NotBlank
    private String content;

    private String createTime;
}
