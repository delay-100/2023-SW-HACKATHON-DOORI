package com.doori.hackerthon.controller;

import com.doori.hackerthon.dto.Chat;
import com.doori.hackerthon.dto.ChatResponse;
import com.doori.hackerthon.service.GptService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.python.core.Py;
import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/chat")
public class ChatController {

    private static PythonInterpreter interpreter;
    private final GptService gptService;

    @ResponseBody
    @GetMapping("/python-data")
    public String getPythonData(@RequestParam String system, @RequestParam String user) {
        String pythonData = gptService.callPythonAPI(system, user);
        System.out.println("----------");
        System.out.println(pythonData);
        System.out.println("----------");

        return pythonData;
    }

    @ResponseBody
    @GetMapping("/pyTest1")
    public String pytest1(@RequestParam("param1") String param1, @RequestParam("param2") String param2) throws UnsupportedEncodingException {

        interpreter = new PythonInterpreter();
        interpreter.execfile("src/main/bot/chatgpt.py");
        PyFunction add_strings = interpreter.get("retriever", PyFunction.class);
        PyObject pyObject = add_strings.__call__(Py.newString(param1), Py.newString(param2));

        String originalStr = pyObject.toString(); // 테스트
        String s = new String(originalStr.getBytes("iso-8859-1"), "utf-8");
        return s;
        }

    @ResponseBody
    @GetMapping("/pyTest2")
    public String pytest2(@RequestParam("param1") String param1, @RequestParam("param2") String param2) throws UnsupportedEncodingException {

        interpreter = new PythonInterpreter();
        interpreter.execfile("src/main/bot/chatgpt.py");
        PyFunction add_strings = interpreter.get("retriever", PyFunction.class);
        PyObject pyObject = add_strings.__call__(Py.newString(param1), Py.newString(param2));

        String originalStr = pyObject.toString(); // 테스트
        String s = new String(originalStr.getBytes("iso-8859-1"), "utf-8");
        return s;
    }
    // 채팅방 조회
    @PostMapping("/room")
    public String getRoom(Model model) {
//        xModelAndView mv = new ModelAndView("chat");
//        mv.addObject("room", chatRoomRepository.findByRoomId(roomId));
        List<String> chatbotList = Arrays.asList("힌트", "요약 보기", "도움말");

        model.addAttribute("chatbotList", chatbotList);
//        model.addAttribute("userId" );
        return "chat";
    }

    // 채팅방 조회
    @PostMapping("/message")
    @ResponseBody
    public ChatResponse chatMessage(@RequestBody Chat chat) {
        return gptService.getChatResponse(chat);
    }

    @PostMapping("/chatbot")
    @ResponseBody
    public ChatResponse callChatBot(@RequestBody Chat chat) {

        return gptService.getChatDocument(chat);
    }
}
