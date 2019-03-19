package com.otakuy.otakuymusic.util;

import com.otakuy.otakuymusic.exception.UnsupportedFormatException;
import com.otakuy.otakuymusic.model.Result;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;
import java.util.Objects;

@Component
public class UploadImageUtil {
    private MultiValueMap<String, String> formData;
    @Setter
    private String cookie;

    public UploadImageUtil(@Value("${sina.username}") String username, @Value("${sina.password}") String password) {
        formData = new LinkedMultiValueMap<>() {{
            add("entry", "sso");
            add("gateway", "1");
            add("from", "null");
            add("savestate", "30");
            add("useticket", "0");
            add("pagerefer", "");
            add("vsnf", "1");
            add("su", Base64.getEncoder().encodeToString(username.getBytes()));
            add("service", "sso");
            add("sp", password);
            add("sr", "1920*1080");
            add("encoding", "UTF-8");
            add("cdult", "3");
            add("domain", "sina.com.cn");
            add("prelt", "0");
            add("returntype", "TEXT");
        }};
        setSinaCookies();
    }

    @Scheduled(cron = "0 0 */1 * * ?")
    public void setSinaCookies() {
        WebClient.create()
                .post()
                .uri("https://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.15)&_=1403138799543")
                .syncBody(formData)
                .exchange()
                .map(ClientResponse::cookies)
                .subscribe(m -> {
                    setCookie("SUB=" + Objects.requireNonNull(m.getFirst("SUB")).getValue() + ";");
                });
    }

    public Mono<String> uploadImg(String path) {
        return WebClient.create()
                .post()
                .uri("http://picupload.weibo.com/interface/pic_upload.php?s=xml&ori=1&data=1&rotate=0&wm=&app=miniblog&mime=image%2Fjpeg")
                .header("Cookie", cookie)
                .header("Connection", "Keep-Alive")
                .body(BodyInserters.fromObject(new FileSystemResource(path)))
                .accept(MediaType.APPLICATION_JSON).retrieve().bodyToMono(String.class).map(s -> "https://ws4.sinaimg.cn/large/" + s.substring(s.indexOf("<pid>") + 5, s.indexOf("</pid>")) + ".jpg");
    }

    public Flux<DataBuffer> uploadImage(FilePart filePart, String path) throws IOException {
        String filename = filePart.filename();
        if (!filename.endsWith(".jpg") && !filename.endsWith(".png"))
            throw new UnsupportedFormatException(new Result<>(HttpStatus.BAD_REQUEST, "图片格式不支持,上传失败"));
        Path image = Paths.get(path);
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(image, StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING, StandardOpenOption.WRITE);
        return DataBufferUtils.write(filePart.content(), channel, 0)
                .doOnError(x -> {
                    throw new UnsupportedFormatException(new Result<>(HttpStatus.BAD_REQUEST, "未知原因导致上传失败"));
                });
    }

}
