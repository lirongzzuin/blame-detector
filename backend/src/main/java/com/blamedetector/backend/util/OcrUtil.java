package com.blamedetector.backend.util;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class OcrUtil {

    public List<String> extractText(MultipartFile file) {
        try {
            // MultipartFile을 임시 파일로 저장
            File convFile = File.createTempFile("temp", ".jpg");
            file.transferTo(convFile);

            Tesseract tesseract = new Tesseract();
            tesseract.setDatapath("/usr/local/share/tessdata"); // Mac/Linux
            // Windows 예시: "C:/Program Files/Tesseract-OCR/tessdata"

            tesseract.setLanguage("kor+eng");

            String text = tesseract.doOCR(convFile);
            convFile.delete();

            // 줄바꿈 단위로 분할하여 메시지 리스트 반환
            return Arrays.asList(text.split("\\r?\\n"));

        } catch (IOException | TesseractException e) {
            throw new RuntimeException("OCR 분석 실패: " + e.getMessage(), e);
        }
    }
}
