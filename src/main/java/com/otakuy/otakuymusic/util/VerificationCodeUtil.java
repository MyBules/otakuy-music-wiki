package com.otakuy.otakuymusic.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.Random;

@Component
public class VerificationCodeUtil {
    private final static int WIDTH;
    private final static int HEIGHT;
    private final static int CODE_COUNT;
    private final static int LINE_COUNT;
    private final static Random random;

    static {
        WIDTH = 200;
        HEIGHT = 60;
        CODE_COUNT = 4;
        LINE_COUNT = 40;
        random = new Random();
    }

    @Data
    @NoArgsConstructor
    @Document(collection = "verificationCode")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class VerificationCode {
        @Id
        private String id;
        @JsonIgnore
        private String code;
        @Transient
        private String imageBase64;
        private Date createtime;
        private String content;

        public VerificationCode(String id, String code) {
            this.id = id;
            this.code = code;
        }

        public VerificationCode(String id, String code, String content) {
            this.id = id;
            this.code = code;
            this.content = content;
        }
    }

    public VerificationCode creatVerificationCode(String email) {
        VerificationCode verificationCode = new VerificationCode();
        verificationCode.setCode(randomStr(CODE_COUNT));
        verificationCode.setContent(email);
        return verificationCode;
    }

    public VerificationCode creatVerificationCode() {
        VerificationCode verificationCode = new VerificationCode();
        int fontWidth = WIDTH / CODE_COUNT;// 字体的宽度
        int fontHeight = HEIGHT - 5;// 字体的高度
        int codeY = HEIGHT - 8;
        BufferedImage bufferedImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics g = bufferedImage.getGraphics();
        g.setColor(getRandColor(250, 255));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        g.setFont(new Font("Fixedsys", Font.BOLD, fontHeight));
        for (int i = 0; i < LINE_COUNT; i++) {
            int xs = random.nextInt(WIDTH);
            int ys = random.nextInt(HEIGHT);
            int xe = xs + random.nextInt(WIDTH);
            int ye = ys + random.nextInt(HEIGHT);
            g.setColor(getRandColor(200, 250));
            g.drawLine(xs, ys, xe, ye);
        }
        float yawpRate = 0.01f;
        int area = (int) (yawpRate * WIDTH * HEIGHT);
        for (int i = 0; i < area; i++) {
            int x = random.nextInt(WIDTH);
            int y = random.nextInt(HEIGHT);
            bufferedImage.setRGB(x, y, random.nextInt(255));
        }
        verificationCode.setCode(randomStr(CODE_COUNT));
        for (int i = 0; i < CODE_COUNT; i++) {
            String strRand = verificationCode.getCode().substring(i, i + 1);
            g.setColor(getRandColor(1, 255));
            g.drawString(strRand, i * fontWidth + 3, codeY);
        }
        g.dispose();
        ByteArrayOutputStream bs = null;
        try {
            bs = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "png", bs);//将绘制得图片输出到流
            verificationCode.setImageBase64(Base64.getEncoder().encodeToString(bs.toByteArray()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bs.close();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                bs = null;
                bufferedImage = null;
            }
        }
        verificationCode.setCreatetime(new Date());
        return verificationCode;
    }

    private String randomStr(int n) {
        String str1 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz1234567890";
        String str2 = "";
        int len = str1.length() - 1;
        double r;
        for (int i = 0; i < n; i++) {
            r = (Math.random()) * len;
            str2 = str2 + str1.charAt((int) r);
        }
        return str2;
    }

    private Color getRandColor(int fc, int bc) {// 给定范围获得随机颜色
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }

}
