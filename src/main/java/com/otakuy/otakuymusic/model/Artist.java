package com.otakuy.otakuymusic.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Artist {
    @Id
    private int id;
    private String name;

    public Artist(String name) {
        this.name = name;
    }

}
