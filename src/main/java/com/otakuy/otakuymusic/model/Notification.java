package com.otakuy.otakuymusic.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

@Data
@NoArgsConstructor
@Document(collection = "notification")
public class Notification {
    @Id
    private String id;
    private String owner;
    private String albumId;
    private Boolean isRead;
    private String creatTime;
    private String content;
    public final static HashMap ACTIONMAP;

    public Notification(String owner, String albumId, String content) {
        this.id = null;
        this.owner = owner;
        this.albumId = albumId;
        this.isRead = false;
        this.creatTime = DateFormat.getDateInstance().format(new Date());
        this.content = (String) ACTIONMAP.get(content);
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
