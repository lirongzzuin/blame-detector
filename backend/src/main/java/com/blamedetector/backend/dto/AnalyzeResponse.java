package com.blamedetector.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class AnalyzeResponse {

    private int userARatio;
    private int userBRatio;
    private String reason;

    private List<MessageResult> analyzedMessages;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class MessageResult {
        private String author;   // A or B
        private String text;
        private String emotion;
        private boolean toxic;
    }
}
