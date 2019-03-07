package com.otakuy.otakuymusic.model.douban;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class AlbumSuggestion {
    private String title;
    private String douban_id;
    private String cover;
}
