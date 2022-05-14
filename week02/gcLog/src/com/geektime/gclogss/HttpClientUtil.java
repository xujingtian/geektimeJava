package com.geektime.gclogss;


import com.sun.deploy.net.HttpResponse;
import sun.net.www.http.HttpClient;


import java.io.IOException;
import java.io.InputStream;

public class HttpClientUtil {
    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8801/";
        System.out.println(HttpClientUtil.httpGet(url));

    }

    /**
     * get请求
     * @param url
     * @return
     * @throws IOException
     */
    public static String httpGet(String url) throws IOException {
        HttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse response = client.execute(httpGet);
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream in = entity.getContent();
            byte[] buffer = new byte[in.available()];
            in.read(buffer, 0, in.available());
            return new String(buffer);
        }
        return null;
    }
}