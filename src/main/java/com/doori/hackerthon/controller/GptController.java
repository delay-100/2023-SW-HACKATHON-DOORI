package com.doori.hackerthon.controller;

import com.doori.hackerthon.dto.ResponseModel;
import com.doori.hackerthon.service.GptService;
import com.doori.hackerthon.service.VisionService;
import io.github.flashvayne.chatgpt.dto.chat.MultiChatMessage;
import io.github.flashvayne.chatgpt.dto.chat.MultiChatResponse;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/api/v1/chat-gpt")
public class GptController {
    private final GptService chatService;


    private final ChatgptService chatgptService;
    @Autowired
    private VisionService visionService;
    //chat-gpt 와 간단한 채팅 서비스 소스
    @PostMapping("message")
    public MultiChatResponse test(@RequestBody String question) {
        return chatService.getChatResponse(question);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String classify(@RequestParam("pdfFile") final MultipartFile file, Model model) {
        System.out.println("file: " + file);
        model.addAttribute("pdfFileName", file.getOriginalFilename());
        return visionService.extractContent(file);
    }

    @PostMapping("/multi/send")
    public ResponseModel<String> multiSend(HttpServletRequest request, @RequestBody List<MultiChatMessage> messages) {
        String requestId = UUID.randomUUID().toString();
        log.info("requestId {}, ip {}, send messages : {}", requestId, request.getRemoteHost(), messages.toString());
        if (CollectionUtils.isEmpty(messages)) {
            return ResponseModel.fail("messages can not be empty");
        }
        String responseMessage = chatgptService.multiChat(messages);
        log.info("requestId {}, ip {}, get a reply : {}", requestId, request.getRemoteHost(), responseMessage);
        return ResponseModel.success(responseMessage);
    }


}
