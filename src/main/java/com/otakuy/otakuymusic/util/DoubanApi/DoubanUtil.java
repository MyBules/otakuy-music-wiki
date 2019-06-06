package com.otakuy.otakuymusic.util.DoubanApi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.otakuy.otakuymusic.model.Album;
import com.otakuy.otakuymusic.model.Artist;
import com.otakuy.otakuymusic.model.Tag;
import com.otakuy.otakuymusic.model.Track;
import com.otakuy.otakuymusic.model.douban.AlbumSuggestion;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.ProxyProvider;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class DoubanUtil {
    private WebClient webclient;
    private Pattern suggestionPattern;
    @Value("${douban.authorization}")
    private String authorization;

    public DoubanUtil() {
        HttpClient httpClient = HttpClient.create()
                .tcpConfiguration(tcpClient ->
                        tcpClient.proxy(proxy -> proxy.type(ProxyProvider.Proxy.HTTP).host("127.0.0.1").port(8888)));
        ReactorClientHttpConnector connector = new ReactorClientHttpConnector(httpClient);
        webclient = WebClient
                .builder()
                // .clientConnector(connector)
                .baseUrl("https://api.douban.com")
                .defaultHeader("Accept-Encoding", "br, gzip, deflate")
                .defaultHeader("User-Agent", "api-client/1 com.douban.frodo/6.13.2(156) Android/22 product/m2 vendor/Meizu model/m2  rom/flyme4  network/wifi  platform/mobile")
                //    .defaultHeader("Authorization", authorization)
                .defaultHeader("Accept-Language", " zh-Hans-CN;q=1, ja-JP;q=0.9")
                .build();
        suggestionPattern = Pattern.compile("(?<=(?:\"title\":\"|\"id\":\"|\"image\":\")).+?(?=\")");
    }

    public Mono<List<AlbumSuggestion>> getAlbumSuggestion(String title) throws UnsupportedEncodingException {
        return webclient.get()
                .uri(URI.create("https://api.douban.com/v2/music/search?q=" + URLEncoder.encode(title, "UTF-8") + ""))
                .exchange().flatMap(clientResponse -> clientResponse.bodyToMono(String.class))
                //  .retrieve()
                //   .bodyToMono(String.class)
                .map(s -> {
                    if (s != null) {
                        System.out.println(s);
                        Object[] matchResults = suggestionPattern.matcher(s).results().map(MatchResult::group).toArray();
                        List<AlbumSuggestion> albumSuggestions = new ArrayList<>();
                        int length = matchResults.length;
                        for (int i = 0; i < length; i = i + 3) {
                            albumSuggestions.add(new AlbumSuggestion(unicodeToString(matchResults[i].toString().replace("\\", "")), matchResults[i + 1].toString(), matchResults[i + 2].toString()));
                        }
                        return albumSuggestions;
                    }
                    throw new RuntimeException();
                });
    }

    public Mono<Album> getAlbumDetail(String douban_id) throws IOException {
        System.out.println(douban_id);
        return webclient.get()
                .uri(URI.create("https://api.douban.com/v2/music/" + douban_id + ""))
                //    .header("Authorization", authorization)
                .exchange().flatMap(clientResponse -> clientResponse.bodyToMono(String.class))
                //    .retrieve()
                //  .bodyToMono(String.class)
                .map(s -> {
                    //    System.out.println(s);
                    try {
                        return jsonToAlbum(s, douban_id);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    throw new RuntimeException();
                });

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
        JsonNode attrs = rootNode.path("attrs");
        //评分
        album.setRating(Float.parseFloat(rating.path("average").asText()));
        //评分人数
        album.setRating_count(rating.path("numRaters").asInt());
        //发行日期
        album.setPubdate(attrs.path("pubdate").toString().replace("\"]", "").replace("[\"", ""));
        //封面
        //  album.setCover(rootNode.path("pic").path("large").asText());
        //介绍
        album.setIntro(rootNode.path("summary").asText());
        //流派
        album.setGenres(rootNode.path("genres").toString().replace("\"]", "").replace("[\"", ""));
        //标题
        album.setTitle(rootNode.path("title").asText());
        //类型
        album.setVersion(attrs.path("version").toString().replace("\"]", "").replace("[\"", ""));
        //标签
        ArrayList<Tag> tagList = new ArrayList<>();
        for (JsonNode tag : rootNode.path("tags")) {
            tagList.add(new Tag(tag.path("name").asText()));
        }
        album.setTags(tagList);
        //发行者
        album.setPublisher(attrs.path("publisher").toString().replace("\"]", "").replace("[\"", ""));
        //艺术家
        ArrayList<Artist> artistList = new ArrayList<>();
        for (JsonNode arist : rootNode.path("author")) {
            artistList.add(new Artist(arist.path("name").asText()));
        }
        album.setArtists(artistList);
        //曲目列表
        ArrayList<Track> trackList = new ArrayList<>();
        System.out.println("sdsds\\ndsds");
        System.out.println(Arrays.asList(attrs.path("tracks").toString().replace("\\n", "-").split("-")));
        Arrays.stream(attrs.path("tracks").toString().replace("\"]", "").replace("[\"", "").replace("\\n", "-").split("-")).forEach(s -> {
            Track track = new Track();
            track.setTitle(s);
            trackList.add(track);
        });
/*            Track track = new Track();
            track.setTitle(songs.path("title").asText());
            if (!songs.path("preview_url").isNull())
                track.setPreview(songs.path("preview_url").asText());
            trackList.add(track);
        }*/
        album.setTracks(trackList);
        //豆瓣链接
        album.setDouban_url("https://music.douban.com/subject/" + douban_id + "/");
        return album;
    }

}
