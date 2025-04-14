package com.blamedetector.backend.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class AnalyzeRequest {
    private MultipartFile image;
}
