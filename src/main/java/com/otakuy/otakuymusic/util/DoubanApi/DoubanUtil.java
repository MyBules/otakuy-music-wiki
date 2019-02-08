package com.otakuy.otakuymusic.util.DoubanApi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.otakuy.otakuymusic.model.Album;
import com.otakuy.otakuymusic.model.Artist;
import com.otakuy.otakuymusic.model.Tag;
import com.otakuy.otakuymusic.model.Track;
import com.otakuy.otakuymusic.model.douban.AlbumSuggestion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DoubanUtil {
    private WebClient webclient;
    private Pattern suggestionPattern;

    public DoubanUtil(@Value("${douban.authorization}") String authorization) {
        webclient = WebClient
                .builder()
                .baseUrl("https://frodo.douban.com")
                .defaultHeader("Accept-Encoding", "br, gzip, deflate")
                .defaultHeader("User-Agent", "api-client/0.1.3 com.douban.frodo/6.9.1 iOS/12.1.3 model/iPhone7,2 network/wifi")
                .defaultHeader("Authorization", authorization)
                .defaultHeader("Accept-Language", " zh-Hans-CN;q=1, ja-JP;q=0.9")
                .defaultUriVariables(Collections.singletonMap("url", "https://frodo.douban.com"))
                .build();
        suggestionPattern = Pattern.compile("(?<=(?:\"title\": \"|music\\\\/|\"cover_url\": \")).+?(?=\")");
    }

    public List<AlbumSuggestion> getAlbumSuggestion(String title) {
        String result = Objects.requireNonNull(webclient.get()
                .uri(URI.create("https://frodo.douban.com/api/v2/search/music?count=15&q=" + title + "&start=0&version=6.9.1&sort=T"))
                .exchange().block())
                .bodyToMono(String.class).block();
        assert result != null;
        Matcher m = suggestionPattern.matcher(result);
        Object[] matchResults = m.results().map(MatchResult::group).toArray();
        List<AlbumSuggestion> albumSuggestions = new ArrayList<>();
        int length = matchResults.length;
        for (int i = 0; i < length; i = i + 3) {
            albumSuggestions.add(new AlbumSuggestion(unicodeToString(matchResults[i].toString()), matchResults[i + 1].toString(), matchResults[i + 2].toString().replace("\\", "")));
        }
        return albumSuggestions;
    }

    public Album getAlbumDetail(String douban_id) throws IOException {
        String result = Objects.requireNonNull(webclient.get()
                .uri(URI.create("https://frodo.douban.com/api/v2/music/" + douban_id))
                .exchange().block())
                .bodyToMono(String.class).block();
        return jsonToAlbum(result, douban_id);
    }

    private static String unicodeToString(String unicodeString) {
        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");
        Matcher matcher = pattern.matcher(unicodeString);
        char ch;
        while (matcher.find()) {
            ch = (char) Integer.parseInt(matcher.group(2), 16);
            unicodeString = unicodeString.replace(matcher.group(1), ch + "");
        }
        return unicodeString;

    }

    private Album jsonToAlbum(String json, String douban_id) throws IOException {
        Album album = new Album();
        ObjectMapper mapper = new ObjectMapper();
        JsonNode rootNode = mapper.readTree(json);
        JsonNode rating = rootNode.path("rating");
        //评分
        album.setRating(Float.parseFloat(rating.path("value").asText()));
        //评分人数
        album.setRating_count(rating.path("count").asInt());
        //发行日期
        album.setPubdate(rootNode.path("pubdate").toString().replace("\"]", "").replace("[\"", ""));
        //封面
        album.setCover(rootNode.path("pic").path("large").asText());
        //介绍
        album.setIntro(rootNode.path("intro").asText());
        //流派
        album.setGenres(rootNode.path("genres").toString());
        //标题
        album.setTitle(rootNode.path("title").asText());
        //类型
        album.setVersion(rootNode.path("version").toString());
        //标签
        ArrayList<Tag> tagList = new ArrayList<>();
        for (JsonNode tag : rootNode.path("tags")) {
            tagList.add(new Tag(tag.path("name").asText()));
        }
        album.setTags(tagList);
        //发行者
        album.setPublisher(rootNode.path("publisher").toString());
        //艺术家
        ArrayList<Artist> artistList = new ArrayList<>();
        for (JsonNode tag : rootNode.path("singer")) {
            artistList.add(new Artist(tag.path("name").asText()));
        }
        album.setArtists(artistList);
        //曲目列表
        ArrayList<Track> trackList = new ArrayList<>();
        for (JsonNode songs : rootNode.path("songs")) {
            Track track = new Track();
            track.setTitle(songs.path("title").asText());
            if (!songs.path("preview_url").isNull())
                track.setPreview(songs.path("preview_url").asText());
            trackList.add(track);
        }
        album.setTracks(trackList);
        //豆瓣链接
        album.setDouban_url("https://music.douban.com/subject/" + douban_id + "/");
        return album;
    }

}
