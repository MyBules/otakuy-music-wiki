package com.otakuy.otakuymusic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Track {
    private String title;
    private String preview;

    public Track(String title) {
        this.title = title;
    }
}
