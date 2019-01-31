package com.otakuy.otakuymusic.model;

import java.util.ArrayList;

public class Album {
    private int album_id;
    private String album_title;
    private ArrayList<Track> tracks;
    private ArrayList<Artist> artists;
    private String album_pubdate;
    private String album_publisher;
    private String album_genres;
    private String album_version;
    private ArrayList<Tag> tags;
    private String album_intro;
    private String album_cover;
    private String album_doubanUrl;
    private String album_code;
    private float album_rating;
    private int album_ratingCount;

    private ArrayList<Album> album_recommendList;
    private int doubanid;

    public int getAlbum_id() {
        return album_id;
    }

    public void setAlbum_id(int album_id) {
        this.album_id = album_id;
    }

    public String getAlbum_title() {
        return album_title;
    }

    public void setAlbum_title(String album_title) {
        this.album_title = album_title;
    }

    public ArrayList<Track> getTracks() {
        return tracks;
    }

    public void setTracks(ArrayList<Track> tracks) {
        this.tracks = tracks;
    }

    public ArrayList<Artist> getArtists() {
        return artists;
    }

    public void setArtists(ArrayList<Artist> artists) {
        this.artists = artists;
    }

    public String getAlbum_pubdate() {
        return album_pubdate;
    }

    public void setAlbum_pubdate(String album_pubdate) {
        this.album_pubdate = album_pubdate;
    }

    public String getAlbum_publisher() {
        return album_publisher;
    }

    public void setAlbum_publisher(String album_publisher) {
        this.album_publisher = album_publisher;
    }

    public String getAlbum_genres() {
        return album_genres;
    }

    public void setAlbum_genres(String album_genres) {
        this.album_genres = album_genres;
    }

    public String getAlbum_version() {
        return album_version;
    }

    public void setAlbum_version(String album_version) {
        this.album_version = album_version;
    }

    public ArrayList<Tag> getTags() {
        return tags;
    }

    public void setTags(ArrayList<Tag> tags) {
        this.tags = tags;
    }

    public String getAlbum_intro() {
        return album_intro;
    }

    public void setAlbum_intro(String album_intro) {
        this.album_intro = album_intro;
    }

    public String getAlbum_cover() {
        return album_cover;
    }

    public void setAlbum_cover(String album_cover) {
        this.album_cover = album_cover;
    }

    public String getAlbum_doubanUrl() {
        return album_doubanUrl;
    }

    public void setAlbum_doubanUrl(String album_doubanUrl) {
        this.album_doubanUrl = album_doubanUrl;
    }

    public String getAlbum_code() {
        return album_code;
    }

    public void setAlbum_code(String album_code) {
        this.album_code = album_code;
    }

    public float getAlbum_rating() {
        return album_rating;
    }

    public void setAlbum_rating(float album_rating) {
        this.album_rating = album_rating;
    }

    public ArrayList<Album> getAlbum_recommendList() {
        return album_recommendList;
    }

    public void setAlbum_recommendList(ArrayList<Album> album_recommendList) {
        this.album_recommendList = album_recommendList;
    }

    public int getDoubanid() {
        return doubanid;
    }

    public void setDoubanid(int doubanid) {
        this.doubanid = doubanid;
    }

    public int getAlbum_ratingCount() {
        return album_ratingCount;
    }

    public void setAlbum_ratingCount(int album_ratingCount) {
        this.album_ratingCount = album_ratingCount;
    }

    @Override
    public String toString() {
        return "Album{" +
                "album_id=" + album_id +
                ", album_title='" + album_title + '\'' +
                ", tracks=" + tracks +
                ", artists=" + artists +
                ", album_pubdate='" + album_pubdate + '\'' +
                ", album_publisher='" + album_publisher + '\'' +
                ", album_genres='" + album_genres + '\'' +
                ", album_version='" + album_version + '\'' +
                ", tags=" + tags +
                ", album_intro='" + album_intro + '\'' +
                ", album_cover='" + album_cover + '\'' +
                ", album_doubanUrl='" + album_doubanUrl + '\'' +
                ", album_code='" + album_code + '\'' +
                ", album_rating=" + album_rating +
                ", album_ratingCount=" + album_ratingCount +
                ", album_recommendList=" + album_recommendList +
                ", doubanid=" + doubanid +
                '}';
    }
}
