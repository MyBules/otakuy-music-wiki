package com.otakuy.otakuymusic.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DownloadRes {
    private Integer permission;
    private String url;
    private String password;
    private String unzipKey;
}
