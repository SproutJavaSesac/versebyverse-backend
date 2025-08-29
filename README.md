# Verse by Verse 📝✨

> AI가 당신의 일상을 문학으로 변환해주는 소셜 플랫폼

[![Version](https://img.shields.io/badge/version-0.1.0-blue.svg)](https://github.com/your-repo/verse-by-verse)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Next.js](https://img.shields.io/badge/Next.js-15-black.svg)](https://nextjs.org/)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

---

## 목차

## 팀원 소개

> **TEAM verseByVerse**

|                                                  강소연                                                  |                                                 박민석                                                  |                                            이상준                                            |                                                Sooamazing                                                |
| :------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------: |
| <img src="https://github.com/user-attachments/assets/7ff482f5-7697-482a-8197-baf6f7136a11" width="150" > | <img src="https://github.com/user-attachments/assets/bf52cc39-f60e-40c5-9928-4c3e09e91d5b" width="150"> | <img src="https://github.com/user-attachments/assets/2acb0026-3185-4168-9ff3-d2febc47db7f" > | <img src="https://github.com/user-attachments/assets/41d6e961-29e4-4910-8e99-db72940c0a22" width="150" > |
|                                [@wayandway](https://github.com/wayandway)                                |                                  [@un0211](https://github.com/un0211)                                   |                     [@oceanlee-seoul](https://github.com/oceanlee-seoul)                     |                               [@Sooamazing](https://github.com/Sooamazing)                               |
|                                유저기능, 대시보드 페이지, 할 일 카드 모달                                |                               헤더, 계정관리 페이지, 대시보드 관리 페이지                               |                      전역 모달 관리, 카드 생성/수정 모달, 대시보드 변경                      |                                  사이드바, 내 대시보드 페이지(SSR 적용)                                  |
|                                              할 일 카드 DnD                                              |                                초대 알림, 대시보드 공유, 비밀번호 암호화                                |                                                                                              |                                       대시보드 즐겨찾기, 다크모드                                        |
|                                       Tanstack Query 및 Redux 설정                                       |                                     깃/깃허브 관리, 문서 관리, 배포                                     |                                    발표 자료 준비 및 발표                                    |                                              회의 내용 정리                                              |

## 📋 서비스 개요

### 🎯 서비스 소개 및 목적

**Verse by Verse**는 사용자가 작성한 일상의 글을 AI가 문학적으로 다듬어주는 혁신적인 소셜 플랫폼입니다.

**개발 기간**: 2024년 12월 ~ 2025년 2월 (3개월, 스프린트 3주 단위)

- **Sprint 1**: MVP 개발 (핵심 기능)
- **Sprint 2**: 소셜 기능 및 AI 고도화
- **Sprint 3**: QA, 성능 최적화, 배포
- **Sprint 4**: 추가 기능 및 안정화

### 🤔 이 프로젝트는 무엇을 위한 것인가?

- **창작의 민주화**: 문학적 재능이 없어도 누구나 아름다운 글을 쓸 수 있도록 지원
- **감정 표현의 확장**: 일상의 소소한 감정을 문학적으로 승화시켜 표현
- **AI와의 협업**: 인간의 진정성과 AI의 표현력을 결합한 새로운 창작 경험

### 💡 어떤 문제를 해결할 수 있는가?

1. **글쓰기 장벽 해소**: "글을 잘 못 써서" 표현을 포기하는 사람들을 위한 솔루션
2. **감정 표현의 한계**: 단순한 일기나 SNS 포스팅을 넘어선 깊이 있는 표현
3. **창작 동기 부족**: AI의 도움으로 자신의 글이 문학작품으로 변화하는 성취감 제공
4. **소통의 질 향상**: 더 풍부하고 아름다운 언어로 타인과 소통

### 👥 어떤 사람들이 이 프로젝트를 사용하면 좋은가?

**주요 타겟 (얼리어답터)**:

- 📝 **창작 지망생**: 글쓰기를 배우고 싶지만 시작이 어려운 사람들
- 🎨 **감성적 표현을 좋아하는 사람들**: 일상을 아름답게 기록하고 싶은 사람들
- 🤖 **AI 기술에 관심 있는 사용자**: 새로운 AI 서비스를 적극적으로 시도하는 사람들
- 📱 **소셜 미디어 활용자**: 기존 SNS에서 차별화된 콘텐츠를 원하는 사람들

**확장 타겟**:

- 📚 문학 애호가들
- 🏫 교육 관계자 (글쓰기 교육)
- 💼 마케터 (창의적 카피라이팅)

### ⚙️ 이 프로젝트는 어떻게 작동하는가?

```mermaid
graph LR
    A[사용자 글 작성] --> B[감정/장르 선택]
    B --> C[AI 변환 처리]
    C --> D[문학적 결과물]
    D --> E[소셜 공유]
    E --> F[커뮤니티 반응]
```

1. **입력**: 사용자가 일상의 글을 자유롭게 작성
2. **선택**: 감정 태그와 원하는 문학 장르 선택
3. **변환**: Google Vertex AI가 문학적으로 변환
4. **공유**: 변환된 작품을 커뮤니티에 공유
5. **소통**: 다른 사용자들과 댓글, 반응으로 소통

---

## 🆚 경쟁자 분석 ("X vs Y")

### 주요 경쟁 서비스 비교

| 기능                    | Verse by Verse | 브런치 | 네이버 블로그 | ChatGPT      |
| ----------------------- | -------------- | ------ | ------------- | ------------ |
| **AI 문학 변환**        | ✅ 전용 기능   | ❌     | ❌            | 🔶 수동 요청 |
| **감정 기반 장르 추천** | ✅             | ❌     | ❌            | ❌           |
| **소셜 기능**           | ✅             | ✅     | 🔶 제한적     | ❌           |
| **실시간 변환**         | ✅             | ❌     | ❌            | ✅           |
| **모바일 최적화**       | ✅             | ✅     | 🔶            | 🔶           |
| **무료 사용**           | ✅             | ✅     | ✅            | 🔶 제한적    |
| **한국어 특화**         | ✅             | ✅     | ✅            | 🔶           |

### 🎯 왜 Verse by Verse를 선택해야 하는가?

1. **전용 AI 엔진**: 문학 변환에 특화된 AI 프롬프트 엔지니어링
2. **원클릭 변환**: 복잡한 설정 없이 즉시 문학작품 생성
3. **감정 중심 UX**: 사용자의 감정 상태에 맞는 맞춤형 변환
4. **소셜 네트워킹**: 창작물 중심의 건전한 커뮤니티
5. **학습 효과**: AI 변환 과정을 통한 자연스러운 글쓰기 학습

---

## 🖥️ 서비스 화면 구성

### 주요 화면 및 기능

#### 1. 메인 피드

- 최신 게시물 타임라인
- 감정별/장르별 필터링
- 인기 게시물 추천

#### 2. 글 작성 화면

- 원본 글 입력 에디터
- 감정 태그 선택 (6가지)
- 문학 장르 선택 (5가지)
- AI 변환 결과 미리보기

#### 3. 게시물 상세

- 원본 ↔ 변환본 비교 뷰
- 댓글 시스템 (3단계 계층)
- 반응 버튼 (좋아요, 북마크, 공유)

#### 4. 프로필 페이지

- 사용자 작품 모음
- 활동 통계
- 팔로우/팔로워 (향후 구현)

#### 5. 관리자 패널

- 신고 관리
- 욕설 필터 관리
- 사용자 통계

---

## 🚀 프로젝트 시작하기

### 📋 전제조건

- **Java 21** 이상
- **Node.js 18** 이상
- **Docker & Docker Compose**
- **MySQL 8.0**
- **Google Cloud Platform** 계정 (Vertex AI 사용)

### 🔧 설치 및 실행

#### 1. 프로젝트 클론

```bash
git clone https://github.com/your-repo/verse-by-verse.git
cd verse-by-verse
```

#### 2. 환경 변수 설정

```bash
# Backend 환경 변수 (.env)
cp versebyverse-backend/.env.example versebyverse-backend/.env

# 필수 환경 변수 설정
DB_NAME=versebyverse
DB_USERNAME=your_username
DB_PASSWORD=your_password
GOOGLE_CLIENT_ID=your_google_client_id
GOOGLE_CLIENT_SECRET=your_google_client_secret
VERTEX_AI_PROJECT_ID=your_gcp_project_id
```

#### 3. 데이터베이스 실행

```bash
cd versebyverse-backend
docker-compose up -d mysql
```

#### 4. 백엔드 실행

```bash
cd versebyverse-backend
./gradlew bootRun
```

#### 5. 접속 확인

- **Frontend**: http://localhost:3000
- **Backend API**: http://localhost:8080

### 🧪 테스트 실행

````bash
# 백엔드 테스트
cd versebyverse-backend
./gradlew test

---

## 🛠️ 기술 개요 및 구현 상세

### 📚 기술 스택

#### Backend

- **Framework**: Spring Boot 3.5.0
- **Language**: Java 21
- **Security**: Spring Security + OAuth2
- **Database**: MySQL 8.4, Spring Data JPA
- **AI**: Spring AI + Google Vertex AI (Gemini 2.0 Flash)
- **Testing**: JUnit 5, Mockito, TestContainers
- **Build**: Gradle 8.x
- **Code Quality**: SonarQube, JaCoCo, Checkstyle

#### Infrastructure

- **Containerization**: Docker, Docker Compose
- **Database**: MySQL 8.4
- **Monitoring**: Spring Boot Actuator
- **CI/CD**: GitHub Actions (예정)

### 🏗️ 시스템 아키텍처

```mermaid
graph TB
    subgraph "Client Layer"
        A[Next.js Frontend]
        B[Mobile App<br/>향후 계획]
    end

    subgraph "API Gateway"
        C[Spring Boot Backend]
    end

    subgraph "Service Layer"
        D[Auth Service]
        E[Post Service]
        F[Comment Service]
        G[AI Service]
        H[Admin Service]
    end

    subgraph "Data Layer"
        I[MySQL Database]
        J[File Storage<br/>향후 계획]
    end

    subgraph "External Services"
        K[Google OAuth2]
        L[Google Vertex AI]
    end

    A --> C
    B --> C
    C --> D
    C --> E
    C --> F
    C --> G
    C --> H
    D --> I
    E --> I
    F --> I
    G --> L
    H --> I
    D --> K
````

### 📊 ERD (Entity Relationship Diagram)

![Image](https://github.com/user-attachments/assets/907e11a5-d6c2-4c71-a8d5-e328c4b293c2)

### API 설계

[API 설계](https://www.notion.so/hannanana/API-20d33717f91380739342c33da2c7b92e?source=copy_link)

### 🔄 배포 아키텍처

```mermaid
graph LR
    subgraph "Development"
        A[Developer]
        B[Git Repository]
    end

    subgraph "CI/CD Pipeline"
        C[GitHub Actions]
        D[Build & Test]
        E[Docker Build]
    end

    subgraph "Production Environment"
        F[Load Balancer]
        G[Frontend Container]
        H[Backend Container]
        I[MySQL Database]
        J[Redis Cache<br/>향후 계획]
    end

    A --> B
    B --> C
    C --> D
    D --> E
    E --> F
    F --> G
    F --> H
    H --> I
    H --> J
```

---

## 📁 파일 구조

### Backend Structure

```
versebyverse-backend/
├── src/main/java/today/sesac/versebyverse/
│   ├── auth/                    # 인증 관련
│   │   ├── controller/
│   │   ├── service/
│   │   └── dto/
│   ├── member/                  # 회원 관리
│   │   ├── entity/
│   │   ├── repository/
│   │   ├── service/
│   │   └── dto/
│   ├── post/                    # 게시물 관리
│   │   ├── entity/
│   │   ├── repository/
│   │   ├── service/
│   │   ├── controller/
│   │   └── dto/
│   ├── comment/                 # 댓글 시스템
│   ├── ai/                      # AI 서비스
│   ├── ranking/                 # 랭킹 시스템
│   ├── report/                  # 신고 시스템
│   ├── profanity/              # 욕설 필터
│   ├── config/                  # 설정 파일
│   └── common/                  # 공통 유틸리티
├── src/main/resources/
│   ├── application.yml
│   ├── application-local.yml
│   ├── application-prod.yml
│   └── data.sql
└── src/test/                    # 테스트 코드
```

---

## 🧪 Testing

### 테스트 전략

- **단위 테스트**: 비즈니스 로직 검증
- **통합 테스트**: API 엔드포인트 테스트
- **E2E 테스트**: 사용자 시나리오 검증

### 테스트 커버리지

- **목표**: 80% 이상 (예정)
- **도구**: JaCoCo, SonarQube

```bash
# 테스트 실행 및 커버리지 리포트 생성
./gradlew test jacocoTestReport

# 커버리지 확인
open build/jacocoHtml/index.html
```

---

## 📏 프로젝트 규칙

### 🔄 Issue Tracking

- **GitHub Issues** 사용
- **라벨 시스템**: `bug`, `feature`, `enhancement`, `documentation`
- **마일스톤**: 스프린트 단위로 관리

### 📝 Commit Convention

```
<type>(<scope>): <subject>

<body>

<footer>
```

**Types:**

- `feat`: 새로운 기능
- `fix`: 버그 수정
- `docs`: 문서 수정
- `style`: 코드 포맷팅
- `refactor`: 코드 리팩토링
- `test`: 테스트 코드
- `chore`: 빌드 업무 수정

**Example:**

```
feat(auth): add Google OAuth2 login

- Implement OAuth2 configuration
- Add custom user service
- Update security config

Closes #123
```

### 🌿 Branch 전략 (Git Flow)

```
main                    # 프로덕션 배포
├── develop            # 개발 통합
├── feature/auth       # 기능 개발
├── feature/ai-service # 기능 개발
├── hotfix/critical-bug # 긴급 수정
└── release/v1.0.0     # 릴리즈 준비
```

### 🔍 Code Convention

#### Backend (Java)

- **Google Java Style Guide** 준수
- **Checkstyle** 설정 적용
- **SonarLint** 플러그인 사용

```xml
<!-- checkstyle.xml -->
<module name="Checker">
  <module name="TreeWalker">
    <module name="Indentation">
      <property name="basicOffset" value="4"/>
    </module>
  </module>
</module>
```

#### Frontend (TypeScript)

- **ESLint + Prettier** 설정
- **Airbnb Style Guide** 기반
- **자동 포맷팅** 설정

```json
{
  "extends": [
    "next/core-web-vitals",
    "@typescript-eslint/recommended",
    "prettier"
  ],
  "rules": {
    "prefer-const": "error",
    "no-unused-vars": "error"
  }
}
```

### 📋 Pull Request 규칙

1. **브랜치명**: `feature/기능명` 또는 `fix/버그명`
2. **PR 템플릿** 사용
3. **코드 리뷰** 필수 (최소 1명)
4. **테스트 통과** 확인
5. **충돌 해결** 후 머지

---

## 🚀 배포 및 운영

### 배포 환경

- **개발**: Docker Compose
- **스테이징**: Kubernetes (예정)
- **프로덕션**: AWS ECS (예정)

### CI/CD 파이프라인

```yaml
# .github/workflows/ci.yml
name: CI/CD Pipeline
on:
  push:
    branches: [main, develop]
  pull_request:
    branches: [main]

jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 21
        uses: actions/setup-java@v3
        with:
          java-version: "21"
      - name: Run tests
        run: ./gradlew test
```

---

**⭐ 이 프로젝트가 마음에 드신다면 Star를 눌러주세요!**

_마지막 업데이트: 2025년 8월_
