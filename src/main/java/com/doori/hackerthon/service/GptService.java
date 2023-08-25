package com.doori.hackerthon.service;

import com.doori.hackerthon.entity.AdminGptEntity;
import com.doori.hackerthon.repository.AdminGptRepository;
import io.github.flashvayne.chatgpt.dto.chat.MultiChatMessage;
import io.github.flashvayne.chatgpt.dto.chat.MultiChatRequest;
import io.github.flashvayne.chatgpt.dto.chat.MultiChatResponse;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
@Transactional
@Service
@RequiredArgsConstructor
public class GptService {
    private final ChatgptService chatgptService;
    private final AdminGptRepository adminGptRepository;

    public List<String> saveExam(){
        List<String> list = new ArrayList<>();
        list.add("list1");
        list.add("list2");
        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setExam(list);
        System.out.println(adminGptEntity.getExam());
        adminGptRepository.save(adminGptEntity);
        return list;
    }
    public List<String> saveIndex(){
        List<String> list = new ArrayList<>();
        list.add("index1");
        list.add("index2");
        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setIndex(list);
        System.out.println(adminGptEntity.getIndex());
        adminGptRepository.save(adminGptEntity);
        return list;
    }
    public List<String> saveKeyword(){
        List<String> list = new ArrayList<>();
        list.add("keyword1");
        list.add("keyword2");
        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setKeyword(list);
        System.out.println(adminGptEntity.getKeyword());
        adminGptRepository.save(adminGptEntity);
        return list;
    }
    public String saveSummary(){
        String summary  = "summary";
        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setSummary(summary);
        System.out.println(adminGptEntity.getSummary());
        adminGptRepository.save(adminGptEntity);
        return summary;
    }
    public String getSummary(){
        String summary  = "summary";
        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setSummary(summary);
        System.out.println(adminGptEntity.getSummary());
        adminGptRepository.save(adminGptEntity);
        return summary;
    }
    /*public List<String> getIndex(){
        String summary  = "summary";
        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setSummary(summary);
        System.out.println(adminGptEntity.getSummary());
        adminGptRepository.save(adminGptEntity);
        return summary;
    }
    public List<String> getExam(){
        String summary  = "summary";
        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setSummary(summary);
        System.out.println(adminGptEntity.getSummary());
        adminGptRepository.save(adminGptEntity);
        return summary;
    }
    public List<String> getKeyword(){
        String summary  = "summary";
        AdminGptEntity adminGptEntity = new AdminGptEntity();
        adminGptEntity.setSummary(summary);
        System.out.println(adminGptEntity.getSummary());
        adminGptRepository.save(adminGptEntity);
        return summary;
    }*/
    public String callPythonAPI(String system, String user) {
        try {
            HttpClient httpClient = HttpClients.createDefault();
//          HttpGet request = new HttpGet("http://0.0.0.0:8000"); // Python API의 엔드포인트 URL로 변경
            HttpPost request = new HttpPost("http://121.187.22.37:8002/");
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
