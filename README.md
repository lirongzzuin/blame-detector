# 🧠 BlameDetector – AI 과실 분석기

**"우리 대화, 과실 몇 대 몇이야?"**

논쟁 중인 카톡 대화를 업로드하면,  
AI가 대화 내용의 감정/공격성/논리성을 분석해  
**누가 더 잘못했는지 과실 비율**을 내려주는 웹 서비스입니다.

> 🤳 10~30대를 위한 유쾌한 밈 기반 UX  
> 📱 모바일 최적화 / 📤 공유 기능 / 🧠 무료 AI 분석 기반  
> 🚫 로그인 없이 누구나 사용 가능 / 💸 광고 기반 수익 모델

---

## ✨ 주요 기능

| 기능 | 설명 |
|------|------|
| 이미지 업로드 | 카카오톡 대화 캡처 업로드 |
| OCR 처리 | 이미지 속 텍스트 자동 추출 (Tesseract4J) |
| 감정/공격성 분석 | Hugging Face 무료 모델 사용 |
| 과실 계산 | A/B 간 비율 계산 + 공격성 점수 기반 |
| 결과 리포트 | 도넛 차트 + 분석 요약 + 메시지 분석 리스트 |
| 공유 기능 | ✅ 카카오톡 공유, 링크 복사, 이미지 저장 지원 |
| 광고 수익 모델 | 공유/결과 화면에 광고 삽입 예정 |

---

## 📂 폴더 구조

```
blame-detector/
├── backend/            # Spring Boot 서버 (API, OCR, AI 분석)
├── frontend/           # React + Vite + Tailwind 기반 SPA
├── README.md
└── .gitignore
```

---

## 🔐 민감 정보 및 보안 키 관리

**절대 GitHub에 커밋되면 안 되는 항목 예시:**

- `YOUR_KAKAO_JAVASCRIPT_KEY`
- `.env`
- `application.properties` 또는 `application.yml`
- API secret key

> ✅ `YOUR_KAKAO_JAVASCRIPT_KEY`는 **Kakao Developers에서 발급받은 JavaScript 키**로, 실제 운영 시 `.env` 또는 환경변수로 분리해 관리해야 합니다.

---

## 🔧 기술 스택

| 영역 | 기술 |
|------|------|
| 프론트엔드 | React + Vite + Tailwind CSS |
| 백엔드 | Spring Boot (REST API, Tesseract4J OCR, WebClient) |
| 감정 분석 | Hugging Face Transformers API (무료) |
| 시각화 | Chart.js (도넛 차트) |
| 공유 기능 | Web Share API + Kakao 공유 SDK |
| 배포 예정 | Vercel (프론트), Render or EC2 (백엔드)

---

## 🚀 현재까지 구현된 기능 흐름

1. 이미지 업로드 → OCR → 감정/공격성 분석
2. AI 분석 결과를 바탕으로 과실 비율 계산
3. 도넛 차트 + 메시지 리스트 + 판결 사유 렌더링
4. 카카오톡 공유, 링크 복사, 이미지 저장 기능
5. 워터마크 포함된 이미지 캡처 저장 기능
6. 분석 결과 → "다시하기" → 업로드로 복귀 흐름 구성

---

## 🧪 향후 개발 계획

- 결과 페이지 고유 링크화
- 결과 공유 이미지 스타일 고도화
- 모바일 광고 UX 최적화
- 서비스 소개 + 사용 가이드 작성

---

## 🙋 만든 사람

- [이영균 (Lee Younggyun)](https://github.com/lirongzzuin)

---
