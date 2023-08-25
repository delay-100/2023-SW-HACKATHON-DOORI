package com.doori.hackerthon.service;

import io.github.flashvayne.chatgpt.dto.chat.MultiChatMessage;
import io.github.flashvayne.chatgpt.dto.chat.MultiChatRequest;
import io.github.flashvayne.chatgpt.dto.chat.MultiChatResponse;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GptService {
    private final ChatgptService chatgptService;

    public String callPythonAPI() {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpGet request = new HttpGet("http://python-api-url"); // Python API의 엔드포인트 URL로 변경
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

    public MultiChatResponse getChatResponse(String prompt) {


        System.out.println(prompt.length());

//        // ChatGPT 에게 질문을 던집니다.
        MultiChatMessage message1 = new MultiChatMessage();
        message1.setRole("system");
        message1.setContent("너는 대학생 학습 도우미이다.");

        MultiChatMessage message2 = new MultiChatMessage();
        message1.setRole("user");
        message1.setContent(prompt);

        List<MultiChatMessage> list = new ArrayList<>();
        list.add(message1);
        list.add(message2);

        MultiChatRequest chatRequest = new MultiChatRequest();
        chatRequest.setModel("gpt-3.5-turbo");
        chatRequest.setMessages(list);


        return chatgptService.multiChatRequest(chatRequest);
//        return chatgptService.sendMessage(chatRequest);
    }
}
