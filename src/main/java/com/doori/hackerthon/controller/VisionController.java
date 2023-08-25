package com.doori.hackerthon.controller;

import com.doori.hackerthon.service.VisionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1")
public class VisionController {

    @Autowired
    private VisionService visionService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public String classify(@RequestParam("pdfFile") final MultipartFile file, Model model) {
        System.out.println("file: " + file);
        model.addAttribute("pdfFileName", file.getOriginalFilename());
        return visionService.extractContent(file);
//        return
    }
//    @GetMapping("/extract-text")
//    public void extractText() throws Exception {
//        String gcsImagePath = "gs://your-bucket-name/dev/[붙임 2] 산학협력프로젝트 결과물";
//
//        visionService.processGcsFiles();
//
//
//    }

/*  @PostMapping("/extractTextFromPdf")
    public List<String> extractTextFromPdf(
            @RequestParam MultipartFile file) {

        return visionService.extractTextFromPdf(file);
    }*/
}
