package com.blamedetector.backend.controller;

import com.blamedetector.backend.dto.AnalyzeResponse;
import com.blamedetector.backend.service.AnalyzeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/analyze")
@RequiredArgsConstructor
public class AnalyzeController {

    private final AnalyzeService analyzeService;

    @PostMapping
    public ResponseEntity<AnalyzeResponse> analyzeImage(@RequestPart("image") MultipartFile image) {
        AnalyzeResponse result = analyzeService.analyze(image);
        return ResponseEntity.ok(result);
    }
}
