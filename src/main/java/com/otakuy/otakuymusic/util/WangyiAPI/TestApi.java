package com.otakuy.otakuymusic.util.WangyiAPI;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class TestApi {


    private static final String userId = "32689809";

    private static Header[] headers = new Header[4];

    static {
        headers[0]=new BasicHeader("Referer", "http://music.163.com");
        headers[1]=new BasicHeader("Origin", "http://music.163.com");
        headers[2]=new BasicHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.71 Safari/537.36");
        headers[3]=new BasicHeader("Content-Type", "application/x-www-form-urlencoded");
    }

    public static void main(String[] args) throws IOException {
        CloseableHttpClient client= HttpClients.custom().build();
        HttpPost httpPost = new HttpPost("https://music.163.com/weapi/cloudsearch/get/web?csrf_token=");
        httpPost.setHeaders(headers);
        String data = "{\"s\":\"21\",\"type\":\"10\"}";
        Map<String, String> forms = EncryptUtils.encrypt(data);
        List<NameValuePair> list = new ArrayList<NameValuePair>();
        Iterator iterator = forms.entrySet().iterator();
         while(iterator.hasNext()){
            Map.Entry<String,String> elem = (Map.Entry<String, String>) iterator.next();
            list.add(new BasicNameValuePair(elem.getKey(),String.valueOf(elem.getValue())));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(list,"UTF-8");
        httpPost.setEntity(entity);
        HttpResponse response = client.execute(httpPost);
        System.out.println(EntityUtils.toString(response.getEntity()));
deal();
    }
    public static void testCloudSearch() {
        String data = "{\"s\":\"魔杰座\",\"type\":\"10\"}";
        Map<String, String> forms = EncryptUtils.encrypt(data);
        String url = "https://music.163.com/weapi/search/suggest/web?csrf_token=";

    }
    public static void deal() throws IOException {

        CloseableHttpClient client= HttpClients.custom().build();
        HttpGet httpGet = new HttpGet("https://music.163.com/album?id=64812");
        httpGet.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        httpGet.setHeader("Accept-Encoding","gzip, deflate, br");
        httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.9");
        httpGet.setHeader("Connection","keep-alive");
        httpGet.setHeader("Host","music.163.com");
        httpGet.setHeader("Referer","https://music.163.com/");
        httpGet.setHeader("Upgrade-Insecure-Requests","1");
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
        CloseableHttpResponse response = client.execute(httpGet);
        Document parse = Jsoup.parse(EntityUtils.toString(response.getEntity()));
        String tags = parse
                .getElementsByTag("script").eq(0).html();
        String track=parse.getElementById("song-list-pre-data").html();
        System.out.println(tags);
        System.out.println(track);

    }
}