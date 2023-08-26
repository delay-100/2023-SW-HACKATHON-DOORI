package com.doori.hackerthon.controller;

import com.doori.hackerthon.dto.ExamDto;
import com.doori.hackerthon.dto.KeywordDto;
import com.doori.hackerthon.service.AdminGptService;
import com.doori.hackerthon.service.GptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/gptTest")
public class AdminGptController {
    private final AdminGptService adminGptService;

    @PostMapping("/firstExam")
    public void saveExam() {
         adminGptService.saveExam();
    }

//    @GetMapping("/exam")
//    public List<String> getExam() {
//        return adminGptService.getExam();
//    }

    @PostMapping("/keyword")
    public List<String> saveKeyword() {
        return adminGptService.saveKeyword();
    }

    @GetMapping("/keyword")
    public List<KeywordDto> getKeyword() {
        return adminGptService.getKeyword();
    }

    @GetMapping("/tt")
    public void test() {
        System.out.println("-----tsetes---");
    }

    @PostMapping("/summary")
    public String saveSummary() {
        return adminGptService.saveSummary();
    }

    @GetMapping("/summary")
    public String getSummary() {
        return adminGptService.getSummary();
    }
}
