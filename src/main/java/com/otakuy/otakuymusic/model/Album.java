package com.otakuy.otakuymusic.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

@Data
@Document(collection = "album")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Album {
    @Id
    private String id;
    private String music163Id;
    @NotBlank
    private String title;
    private ArrayList<Track> tracks;
    private ArrayList<Artist> artists;
    private String pubdate;
    private String publisher;
    private String genres;
    private String version;
    private ArrayList<Tag> tags;
    @NotBlank
    private String intro;
    @URL
    private String cover;
    private String douban_url;
    private String code;
    private Float rating;
    private Integer rating_count;
    private String owner;
    private DownloadRes downloadRes;

    private String status;
    private Boolean isRecommend;


}
