package com.otakuy.otakuymusic.model;

public class Artist {
    private int artist_id;
    private String artist_name;

    public Artist(String artist_name) {
        this.artist_name = artist_name;
    }

    public int getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(int artist_id) {
        this.artist_id = artist_id;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    @Override
    public String toString() {
        return "Artist{" +
                "artist_id=" + artist_id +
                ", artist_name='" + artist_name + '\'' +
                '}';
    }
}
