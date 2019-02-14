package com.otakuy.otakuymusic.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.HashMap;

@Data
@Document(collection = "notification")
@NoArgsConstructor
public class Notification {
    @Id
    private String id;
    private String owner;
    private String albumId;
    private Boolean isRead;
    private Date creatTime;
    private String content;
    private String url;
    public final static HashMap ACTIONMAP;

    public Notification(String owner, String albumId, String content, String url) {
        this.id = null;
        this.owner = owner;
        this.albumId = albumId;
        this.isRead = false;
        this.creatTime = new Date();
        this.content = (String) ACTIONMAP.get(content);
        this.url = url;
    }

    static {
        ACTIONMAP = new HashMap<String, String>() {{
            put("albumBeCommented", "您维护的专辑收到一条评论");
            put("albumBeStarred", "您维护的专辑收到打赏");
            put("albumBeRequestedRevision", "您维护的专辑收到一条修改请求");

            put("albumBeActive", "您提交的专辑通过审核");
            put("albumBeReject", "您提交的专辑未通过审核");

            put("revisionBeActive", "您提交的修改被采用");
            put("revisionBeReject", "您提交的修改被拒绝");

            put("newAlbumBeCommitted", "新的专辑等待审核");//被拒绝专辑也可以重新编辑后提交审核
            //put("newAlbumBeCommitted", "新的专辑等待审核");
        }};
    }

}
