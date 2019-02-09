package com.otakuy.otakuymusic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "comment")
public class Comment {
    private String album;
    private String pid;
    @Id
    private String id;

    private String user_id;
    private String user_cover;
    private String user_username;

    private String content;
    private String createTime;
}
