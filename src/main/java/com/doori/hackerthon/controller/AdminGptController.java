package com.doori.hackerthon.controller;

import com.doori.hackerthon.dto.ExamDto;
import com.doori.hackerthon.dto.RetestDto;
import com.doori.hackerthon.service.AdminGptService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/exam")
    public String getExam(Model model, @RequestParam Long id) {
        model.addAttribute("exampleList", adminGptService.getExam(id));
        return "exam";
    }
    @ResponseBody
    @PostMapping("/retest")
    public void saveRetest() {
        adminGptService.saveRetest();
    }
    @ResponseBody
    @PostMapping("/tdtest")
    public void saveTdtest() {
        adminGptService.saveTdtest();
    }
    @ResponseBody
    @GetMapping("/exam")
    public List<ExamDto> getRetest(Model model, @RequestParam Long id) {
        List<ExamDto> retestList = adminGptService.getExam(id);
        return retestList;
    }
    @ResponseBody
    @PostMapping("/keyword")
    public List<String> saveKeyword(Model model) {
        return adminGptService.saveKeyword();
    }

    @GetMapping("/keyword")
    public String getKeyword(Model model) {
        model.addAttribute("keywordList", adminGptService.getKeyword());
        return "keyword";
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
