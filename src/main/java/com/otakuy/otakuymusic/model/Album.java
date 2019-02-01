package com.otakuy.otakuymusic.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Data
@Document(collection = "album")
public class Album {
    @Id
    private String id;
    private String title;
    private ArrayList<Track> tracks;
    private ArrayList<Artist> artists;
    private String pubdate;
    private String publisher;
    private String genres;
    private String version;
    private ArrayList<Tag> tags;
    private String intro;
    private String cover;
    private String douban_url;
    private String code;
    private Float rating;
    private Integer rating_count;
    private String owner_id;
    private DownloadRes downloadRes;

}
