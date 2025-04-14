package com.blamedetector.backend.util;

import com.blamedetector.backend.dto.AnalyzeResponse.MessageResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AiAnalysisUtil {

    public List<MessageResult> analyzeMessages(List<String> texts) {
        List<MessageResult> results = new ArrayList<>();

        for (int i = 0; i < texts.size(); i++) {
            String author = (i % 2 == 0) ? "A" : "B";
            String text = texts.get(i);

            // 🔸 더미 감정 분석 결과: B가 toxic 하다는 가정
            results.add(MessageResult.builder()
                    .author(author)
                    .text(text)
                    .emotion(author.equals("B") ? "anger" : "neutral")
                    .toxic(author.equals("B"))
                    .build());
        }

        return results;
    }
}
