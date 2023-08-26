package com.doori.hackerthon.controller;

import com.doori.hackerthon.dto.ExamDto;
import com.doori.hackerthon.service.AdminGptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
@Slf4j
@RequestMapping("/gptTest")
public class AdminGptController {
    private final AdminGptService adminGptService;

    @ResponseBody
    @PostMapping("/firstExam")
    public void saveExam() {
         adminGptService.saveExam();
    }
    @ResponseBody
    @GetMapping("/fistExam")
    public List<ExamDto> getExam() {
        return adminGptService.getExam();
    }

    @PostMapping("/keyword")
    public List<String> saveKeyword(Model model) {
        return adminGptService.saveKeyword();
    }
    @GetMapping("/keyword")
    public String getKeyword(Model model) {
        model.addAttribute("keywordList", adminGptService.saveKeyword());
        return "keyword";
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
