package com.otakuy.otakuymusic.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;

@Data
@Document(collection = "revision")
public class Revision<T> {
    @Id
    private String id;
    @NotBlank
    private String album;
    private String committer;
    private String committerName;
    @NotBlank
    private String modificationPoint;
    @NotNull
    private T content;
    private String status;
    private String createTime;
    public final static HashMap MODIFICATION_POINT_MAP;

    static {
        MODIFICATION_POINT_MAP = new HashMap<String, String>() {{
            put("title", "setTitle");
            put("tracks", "setTracks");
            put("artists", "setArtists");
            put("pubdate", "setPubdate");
            put("publisher", "setPublisher");
            put("genres", "setGenres");
            put("version", "setVersion");
            put("tags", "setTags");
            put("intro", "setIntro");
            put("cover", "setCover");
            put("douban_url", "setDouban_url");
            put("owner_id", "setOwner_id");
            put("downloadRes", "setDownloadRes");
        }};
    }

    public static void init(Revision revision, String committerName, String committer) {
        revision.setCommitterName(committerName);
        revision.setCreateTime(DateFormat.getDateInstance().format(new Date()));
        revision.setCommitter(committer);
    }

}
