package com.otakuy.otakuymusic.model;

import lombok.Data;

@Data
public class DownloadRes {
    private Integer permission;
    private String url;
    private String password;
    private String unzipKey;
}
