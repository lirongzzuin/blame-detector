# 🧠 BlameDetector – AI 과실 분석기

**"우리 대화, 과실 몇 대 몇이야?"**

논쟁 중인 카카오톡 대화를 업로드하면,  
AI가 대화 내용의 감정, 공격성, 흐름을 분석해  
**누가 더 잘못했는지 과실 비율을 알려주는 웹 서비스**입니다.

> 🤳 10~30대를 위한 유쾌한 밈 기반 UX  
> 📱 모바일 최적화 / 📤 공유 기능 / 🧠 무료 AI 분석 기반  
> 🚫 로그인 없이 누구나 사용 가능 / 💸 광고 기반 수익 구조

---

## ✨ 주요 기능

| 기능 | 설명 |
|------|------|
| 이미지 업로드 | 카카오톡 대화 캡처 이미지 업로드 지원 |
| OCR 처리 | Tesseract4J 기반 이미지 내 텍스트 추출 |
| 감정/공격성 분석 | Hugging Face 무료 모델을 통한 AI 분석 |
| 과실 계산 | A/B 사용자 간 공격성 점수 기반 비율 산정 |
| 결과 리포트 | 도넛 차트 + 분석 요약 + 메시지 분석 카드 렌더링 |
| 공유 기능 | ✅ 카카오톡 공유, 링크 복사, 결과 이미지 저장 지원 |
| 광고 수익 모델 | 공유/결과 화면에 자연스러운 광고 삽입 예정 |

---

## 📂 폴더 구조

```
blame-detector/
├── backend/            # Spring Boot 서버 (OCR, AI 분석, API)
├── frontend/           # React + Vite + Tailwind 기반 SPA
├── README.md
└── .gitignore
```

---

## 🔐 민감 정보 및 보안 키 관리

**아래 항목은 GitHub에 절대 커밋되지 않아야 합니다:**

- `.env`
- `application.properties` / `application.yml`
- `YOUR_KAKAO_JAVASCRIPT_KEY`
- Hugging Face API 키 등 외부 서비스 키

> `YOUR_KAKAO_JAVASCRIPT_KEY`는 Kakao Developers에서 발급받은 JavaScript Key이며,  
> 반드시 `.env` 또는 Vercel/Render 환경변수로 분리 관리해야 합니다.

---

## 🔧 기술 스택

| 영역 | 사용 기술 |
|------|-----------|
| 프론트엔드 | React, Vite, Tailwind CSS |
| 백엔드 | Spring Boot, Tesseract4J, WebClient |
| 감정 분석 | Hugging Face Inference API |
| 시각화 | Chart.js (도넛 차트) |
| 공유 기능 | Web Share API, Kakao 공유 SDK |
| 배포 환경 | Vercel (프론트), Render 또는 EC2 (백엔드)

---

## 🚀 현재까지 구현된 주요 흐름

1. 이미지 업로드 → OCR 텍스트 추출  
2. 텍스트 기반 감정 및 공격성 분석  
3. AI 분석 결과 기반 과실 비율 계산  
4. 시각화된 결과 리포트 (도넛 차트 + 메시지 카드)  
5. 카카오톡 공유, 링크 복사, 이미지 저장 기능 지원  
6. 결과 이미지 저장 시 워터마크 삽입 처리  
7. 다시하기 버튼으로 업로드 화면으로 자연스럽게 이동

---

## 🧪 향후 개발 계획

- 분석 결과에 대한 고유 링크 생성 기능  
- 공유 이미지 스타일 및 브랜딩 강화  
- 광고 UI/UX 고도화 및 수익화 실험  
- 사용자 가이드 및 FAQ 문서 정비  
- AI 리포트 PDF 다운로드 기능

---

## 🙋 만든 사람

- [이영균 (Lee Younggyun)](https://github.com/lirongzzuin)

---

## © 저작권 및 소유권 안내

© 2025 Lee Younggyun.  
본 프로젝트 `blame-detector`는 이영균(Lee Younggyun)에 의해 기획, 설계, 개발되었습니다.  
소스 코드, 기능 구성, 서비스 아이디어, UX 흐름, 결과 리포트 형태 등  
모든 창작물의 **저작권과 소유권은 제작자에게 귀속**됩니다.

무단 복제, 유사 서비스 제작, 로직 도용, 결과 리포트 형식의 모방을 금지합니다.  
상업적 활용, 미디어 인용, 기능 이전 또는 협업을 원할 경우 사전 협의가 필요합니다.

> 본 프로젝트는 MVP 형태의 창업 기반 사이드 프로젝트이며,  
> 향후 서비스화 및 사업화 절차가 진행될 수 있습니다.
