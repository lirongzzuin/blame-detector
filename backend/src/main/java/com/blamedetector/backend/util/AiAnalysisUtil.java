package com.blamedetector.backend.util;

import com.blamedetector.backend.dto.AnalyzeResponse.MessageResult;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AiAnalysisUtil {

    private final WebClient webClient = WebClient.create();

    @Value("${huggingface.api.key}")
    private String huggingFaceApiKey;

    public List<MessageResult> analyzeMessages(List<String> texts) {
        List<MessageResult> results = new ArrayList<>();

        for (int i = 0; i < texts.size(); i++) {
            String author = (i % 2 == 0) ? "A" : "B";
            String text = texts.get(i);

            String emotion = callSentimentModel(text);
            boolean toxic = callToxicityModel(text);

            results.add(MessageResult.builder()
                    .author(author)
                    .text(text)
                    .emotion(emotion)
                    .toxic(toxic)
                    .build());
        }

        return results;
    }

    private String callSentimentModel(String text) {
        String url = "https://api-inference.huggingface.co/models/cardiffnlp/twitter-roberta-base-sentiment";
        return callHuggingFaceApi(url, text, "label");
    }

    private boolean callToxicityModel(String text) {
        String url = "https://api-inference.huggingface.co/models/unitary/toxic-bert";
        String label = callHuggingFaceApi(url, text, "label");
        return label.toLowerCase().contains("toxic");
    }

    private String callHuggingFaceApi(String url, String input, String returnKey) {
        try {
            String jsonBody = "{ \"inputs\": \"" + input + "\" }";

            return webClient.post()
                    .uri(url)
                    .header("Authorization", "Bearer " + huggingFaceApiKey)
                    .contentType(MediaType.APPLICATION_JSON)
                    .bodyValue(jsonBody)
                    .retrieve()
                    .bodyToMono(String.class)
                    .map(response -> {
                        // 간단한 JSON 파싱 - production에서는 Jackson 등으로 처리
                        String[] tokens = response.split("\"" + returnKey + "\":\"");
                        if (tokens.length > 1) {
                            return tokens[1].split("\"")[0];
                        }
                        return "neutral";
                    })
                    .onErrorReturn("neutral")
                    .block(); // 동기 처리 (WebClient지만 내부 블록)

        } catch (Exception e) {
            return "neutral";
        }
    }
}
