package com.doori.hackerthon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {
    @GetMapping("")
    public String home(Model model) {
        // 학과
        List<String> departmentList = Arrays.asList("컴퓨터공학과", "전자공학과", "경영학과", "신문방송학과", "심리학과", "생물학과");
        // 과목
        List<String> subjectList = Arrays.asList("운영체제", "네트워크 프로그래밍", "알고리즘", "자료구조", "프로그래밍 기초", "인공지능");

        model.addAttribute("departmentList", departmentList);
        model.addAttribute("subjectList", subjectList);

        return "home";
    }

    @GetMapping("/calculator")
    public String getCalculator() {
        return "/calculator";
    }

    @GetMapping("/exam")
    public String getExam() {
        return "/exam";
    }

    @GetMapping("/admin")
    public String getAdminPage() {
        return "/admin/uploadPDF";
    }
}
