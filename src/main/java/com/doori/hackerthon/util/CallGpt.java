package com.doori.hackerthon.util;

import com.doori.hackerthon.dto.InitChat;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;

public class CallGpt {

    public String initStore(String documents, String subjectName) {

        String API_URL = "http://121.187.22.37:8001";
        String body = null;
        Gson gson = new Gson();
        try {
            InitChat initChat = InitChat.builder()
                    .documents("documents")
                    .subject_name(subjectName).build();
            HttpClient client = HttpClientBuilder.create().build();     //HttpClient 생성
            HttpPost httpPost = new HttpPost(API_URL + "/store");
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Accept", "*/*");
            httpPost.setHeader("Connection", "keep-alive");
            String requestBody = gson.toJson(initChat, InitChat.class);
            String charset[] = {"utf-8", "euc-kr", "ksc5601", "iso-8859-1", "8859_1", "ascii", "utf-16"};

            for (String before : charset) {
                for (String after : charset) {
                    if (!before.equals(after)) {
                        System.out.println(before + " -> " + after + " = " + new String(requestBody.getBytes(before), after));
                    }
                }
            }

            httpPost.setEntity(new StringEntity(gson.toJson(initChat, InitChat.class)));
            System.out.println(gson.toJson(initChat, InitChat.class));
            HttpResponse response = client.execute(httpPost);
            //Response 출력
            if (response.getStatusLine().getStatusCode() == 200) {
                ResponseHandler<String> handler = new BasicResponseHandler();
                body = handler.handleResponse(response);
                System.out.println(body);
            } else {
                System.out.println("response is error : " + response.getStatusLine().getStatusCode());
            }
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return body;
    }

    public String initStore2(String documents, String subjectName) {
        try {
            HttpClient httpClient = HttpClients.createDefault();
//            HttpGet request = new HttpGet("http://0.0.0.0:8000"); // Python API의 엔드포인트 URL로 변경
            //    @Value("${uri}")
            String API_URL = "http://121.187.22.37:8001";
            HttpPost request = new HttpPost(API_URL + "/store");
            URI uri = new URIBuilder(request.getURI())
                    .addParameter("subject_name", subjectName)
                    .addParameter("documents", documents)
                    .build();
            request.setURI(uri);
            HttpResponse response = httpClient.execute(request);

            // 응답 데이터 처리
            if (response.getStatusLine().getStatusCode() == 200) {
                String responseBody = EntityUtils.toString(response.getEntity());
                return responseBody;
            } else {
                // 오류 처리
                return "Error: " + response.getStatusLine().getStatusCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    public static String chatMessage(String system, String user) {
        try {
            HttpClient httpClient = HttpClients.createDefault();
//            HttpGet request = new HttpGet("http://0.0.0.0:8000"); // Python API의 엔드포인트 URL로 변경
            HttpPost request = new HttpPost("http://121.187.22.37:8001");
            URI uri = new URIBuilder(request.getURI())
                    .addParameter("system", system)
                    .addParameter("user", user)
                    .build();
            request.setURI(uri);
            HttpResponse response = httpClient.execute(request);

            // 응답 데이터 처리
            if (response.getStatusLine().getStatusCode() == 200) {
                String responseBody = EntityUtils.toString(response.getEntity());
                return responseBody;
            } else {
                // 오류 처리
                return "Error: " + response.getStatusLine().getStatusCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
}
