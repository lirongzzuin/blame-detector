package com.blamedetector.backend.service;

import com.blamedetector.backend.dto.AnalyzeResponse;
import com.blamedetector.backend.dto.AnalyzeResponse.MessageResult;
import com.blamedetector.backend.util.AiAnalysisUtil;
import com.blamedetector.backend.util.OcrUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyzeService {

    private final OcrUtil ocrUtil;
    private final AiAnalysisUtil aiAnalysisUtil;

    public AnalyzeResponse analyze(MultipartFile image) {

        // 1. OCR 처리 (더미)
        List<String> rawTexts = ocrUtil.extractText(image);

        // 2. 사용자 구분 (A/B) (더미: 짝수줄 A, 홀수줄 B)
        List<MessageResult> messages = aiAnalysisUtil.analyzeMessages(rawTexts);

        // 3. 간단한 과실 계산 로직 (더미)
        int toxicCountA = (int) messages.stream().filter(m -> m.getAuthor().equals("A") && m.isToxic()).count();
        int toxicCountB = (int) messages.stream().filter(m -> m.getAuthor().equals("B") && m.isToxic()).count();

        int total = toxicCountA + toxicCountB + 1; // +1 to avoid div by 0
        int aRatio = (int) ((toxicCountA / (double) total) * 100);
        int bRatio = 100 - aRatio;

        return AnalyzeResponse.builder()
                .userARatio(aRatio)
                .userBRatio(bRatio)
                .reason("AI 기반 더미 분석 결과입니다. 실제 감정 분석 연결 예정.")
                .analyzedMessages(messages)
                .build();
    }
}
