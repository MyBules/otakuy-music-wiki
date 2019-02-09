package com.otakuy.otakuymusic.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Data
@Document(collection = "revision")
public class Revision<T> {
    @Id
    private String id;
    private String album_id;
    private String committer;
    private String modification_point;
    private T content;
    private String status;
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

}
