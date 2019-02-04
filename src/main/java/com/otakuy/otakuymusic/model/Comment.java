package com.otakuy.otakuymusic.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Comment {
    private String pid;
    @Id
    private String id;
    private String user_id;
    private String content;
    private String createTime;
}
