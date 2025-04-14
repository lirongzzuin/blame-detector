package com.blamedetector.backend.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@Component
public class OcrUtil {

    public List<String> extractText(MultipartFile file) {
        // 🔸 실제 OCR 연결 전 더미 텍스트 반환
        return Arrays.asList(
                "왜 그렇게 말했어?",   // A
                "네가 먼저 그랬잖아",   // B
                "그래도 말이 심했어",   // A
                "짜증나게 하지 마"     // B
        );
    }
}
