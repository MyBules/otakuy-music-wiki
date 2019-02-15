package com.otakuy.otakuymusic.util;

import com.otakuy.otakuymusic.exception.UnsupportedFormatException;
import com.otakuy.otakuymusic.model.Result;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Component
public class UploadImageUtil {
    public void uploadImage(FilePart filePart, String path, Runnable doOnComplete) throws IOException {
        String filename = filePart.filename();
        if (!filename.endsWith(".jpg") && !filename.endsWith(".png"))
            throw new UnsupportedFormatException(new Result<>(HttpStatus.BAD_REQUEST, "图片格式不支持,上传失败"));
        Path image = Paths.get(path);
        if (!Files.exists(image))
            image = Files.createFile(image);
        AsynchronousFileChannel channel = AsynchronousFileChannel.open(image, StandardOpenOption.WRITE);
        DataBufferUtils.write(filePart.content(), channel, 0)
                .doOnError(x -> {
                    throw new UnsupportedFormatException(new Result<>(HttpStatus.BAD_REQUEST, "未知原因导致上传失败"));
                })
                .doOnComplete(doOnComplete)
                .subscribe();
    }


}
