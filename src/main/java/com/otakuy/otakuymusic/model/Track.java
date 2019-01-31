package com.otakuy.otakuymusic.model;

public class Track {
    private String track_title;
    private String track_preview;

    public String getTrack_title() {
        return track_title;
    }

    public void setTrack_title(String track_title) {
        this.track_title = track_title;
    }

    public String getTrack_preview() {
        return track_preview;
    }

    public void setTrack_preview(String track_preview) {
        this.track_preview = track_preview;
    }

    @Override
    public String toString() {
        return "Track{" +
                "track_title='" + track_title + '\'' +
                ", track_preview='" + track_preview + '\'' +
                '}';
    }
}
