package com.otakuy.otakuymusic.model;

import lombok.Data;

@Data
class DownloadRes {
    private String url;
    private String password;
    private String unzipKey;
}
