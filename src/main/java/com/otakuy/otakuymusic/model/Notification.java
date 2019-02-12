package com.otakuy.otakuymusic.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.HashMap;

@Data
public class Notification<T> {
    @Id
    private String id;
    private String albumId;
    private Boolean isRead;
    private String creatTime;
    private T content;
    private String url;
    public final static HashMap ACTIONMAP;

    static {
        ACTIONMAP = new HashMap<String, String>() {{
            put("albumBeCommented", "您维护的专辑收到一条评论");
            put("albumBeStarred", "您维护的专辑收到打赏");
            put("albumBeActive", "您提交的专辑通过审核");

            put("albumBeReject", "您提交的专辑未通过审核");
            put("reviseQuest", "您维护的专辑收到一条修改请求");

            put("revisionBeActive", "您提交的修改被采用");
            put("revisionBeReject", "您提交的修改被拒绝");

            put("newAlbumBeCommitted", "新的专辑等待审核");//被拒绝专辑也可以重新编辑后提交审核
            //put("newAlbumBeCommitted", "新的专辑等待审核");
        }};
    }

}
