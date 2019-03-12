package com.otakuy.otakuymusic.util;

import com.otakuy.otakuymusic.exception.AuthorityException;
import com.otakuy.otakuymusic.model.Album;
import com.otakuy.otakuymusic.model.DownloadRes;
import com.otakuy.otakuymusic.model.Result;
import com.otakuy.otakuymusic.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AlbumUtil {
    private final JWTUtil jwtUtil;

    //新增专辑初始化
    public Album initNew(User user, Album album) {
        album.setId(null);
        album.setOwner(user.getId());
        album.setOwnerAvatar(user.getAvatar());
        album.setRating_count(0);
        album.setRating(0F);
        album.setStatus("block");
        album.setIsRecommend(false);
        album.setCover("https://ws4.sinaimg.cn/large/006346uDgy1g0z824nq5tj30i60i7gli.jpg");
        album.setCreateTime(DateFormat.getDateInstance().format(new Date()));
        if (album.getDownloadRes() == null)
            album.setDownloadRes(new DownloadRes());
        return album;
    }

    //维护者更新专辑
    public Album update(Album oldAlbum, Album album) {
        if (oldAlbum.getStatus().equals("reject"))
            oldAlbum.setStatus("block");
        oldAlbum.setMusic163Id(album.getMusic163Id());
        oldAlbum.setTitle(album.getTitle());
        oldAlbum.setTracks(album.getTracks());
        oldAlbum.setArtists(album.getArtists());
        oldAlbum.setPubdate(album.getPubdate());
        oldAlbum.setPublisher(album.getPublisher());
        oldAlbum.setGenres(album.getGenres());
        oldAlbum.setVersion(album.getVersion());
        oldAlbum.setTags(album.getTags());
        oldAlbum.setIntro(album.getIntro());
        //    oldAlbum.setCover(album.getCover());
        oldAlbum.setDouban_url(album.getDouban_url());
        oldAlbum.setCode(album.getCode());
        oldAlbum.setDownloadRes(album.getDownloadRes());
        return oldAlbum;
    }

    //验证专辑所有权
    public Boolean checkAuthority(String token, Album album) {
        String id = jwtUtil.getId(token);
        if (!album.getOwner().equals(jwtUtil.getId(token)))
            throw new AuthorityException((new Result<>(HttpStatus.UNAUTHORIZED, "权限不足")));
        return true;
    }

    //验证专辑所有权
    public Boolean checkAuthorityWithoutThrowException(String token, Album album) {
        String id = jwtUtil.getId(token);
        if (!album.getOwner().equals(jwtUtil.getId(token)))
            return false;
        return true;
    }

}
