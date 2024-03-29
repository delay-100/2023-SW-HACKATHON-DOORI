package com.doori.hackerthon.controller;


import com.doori.hackerthon.entity.InputFile;
import com.doori.hackerthon.service.FileService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class GcsFileController {

    private static final Logger LOGGER = LoggerFactory.getLogger(GcsFileController.class);

    private final FileService fileService;

    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public List<InputFile> addFile(@RequestParam("file") MultipartFile[] files){
        LOGGER.debug("Call addFile API");
        return fileService.uploadFiles(files);
    }
}
