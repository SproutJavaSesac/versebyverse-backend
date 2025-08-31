# 구절구절 - Verse by Verse 📝✨

> AI가 당신의 일상을 문학으로 변환해주는 소셜 플랫폼

[![Version](https://img.shields.io/badge/version-0.1.0-blue.svg)](https://github.com/your-repo/verse-by-verse)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
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

**개발 기간**: 2025년 6월 ~ 2025년 8월 (3개월, 스프린트 3주 단위)

### 💡 어떤 문제를 해결할 수 있는가?

1. **표현력의 한계**: "짜증나", "기뻐", "슬퍼" 같은 단조로운 감정 표현을 풍부하게 변환
2. **글쓰기 실력 향상**: AI 첨삭을 통해 자연스럽게 어휘력과 표현력 향상
3. **창작 동기 부여**: 내 글이 문학작품으로 변화하는 재미와 성취감 제공
4. **소통의 질 개선**: SNS, 블로그에서 더 매력적이고 감각적인 글쓰기 가능
5. **학습 효과**: 수능, 논술, 글쓰기 실력에 직접적인 도움

### 👥 어떤 사람들이 이 프로젝트를 사용하면 좋은가?

**🎓 학습자 & 수험생**

- 수능, 논술, 글쓰기 실력 향상이 필요한 학생들
- 감정을 다양하게 표현하는 어휘력 확장을 원하는 사람들
- 문학 작품 연계로 국어 성적 향상을 목표로 하는 학습자들

**💼 직장인 & 전문가**

- 업무, 소통에서 전문성을 보여주고 싶은 직장인들
- 기획서, 보고서 작성 시 표현력 향상이 필요한 사람들
- 개인 브랜딩을 위한 글쓰기 실력을 키우고 싶은 전문가들

**✨ 세련된 표현을 원하는 사람들**

- SNS, 일상 대화에서 매력적으로 소통하고 싶은 사람들
- 인스타그램, 블로그 게시글을 더 감각적으로 작성하고 싶은 사용자들
- "글 정말 잘 쓰네요" 칭찬을 받고 싶은 모든 사람들

**📝 글쓰기 연습을 원하는 사람들**

- 체계적인 피드백으로 글쓰기 실력을 늘리고 싶은 사람들
- 매일 부담 없이 글쓰기 연습을 하고 싶은 사람들
- 실제 활용 가능한 실용적 표현을 학습하고 싶은 사람들

### ⚙️ 이 프로젝트는 어떻게 작동하는가?

**Verse by Verse**는 다음과 같은 과정으로 당신의 일상을 문학으로 변환합니다:

```mermaid
graph LR
    A[일상 표현 입력<br/>"진짜 짜증나"] --> B[감정 인식<br/>😤 분노]
    B --> C[AI 프리즘 변환<br/>목적별 스타일 적용]
    C --> D[문학적 결과물<br/>"마음이 무거운 구름에<br/>가려진 듯하다"]
    D --> E[점수 제공<br/>실시간 평가]
    E --> F[소셜 공유<br/>커뮤니티 반응]
```

**🎯 핵심 변환 예시**:

- **변환 전**: "오늘 진짜 짜증났어. 모든 게 다 싫다."
- **변환 후**: "오늘 마음이 무거운 구름에 가려진 듯하다. 모든 것이 잿빛으로 물들어 보인다."

**✨ 특별한 기능들**:

1. **목적별 AI 첨삭**: 학술용, 전문가용, SNS용으로 각각 다른 톤앤매너
2. **실시간 점수 시스템**: 작문 실력, 창의성, 감정 표현도 점수 제공
3. **랭킹 & 소셜**: 다른 사용자들과 점수 비교 및 동기부여
4. **SNS 공유**: 첨삭된 글을 바로 인스타, 블로그에 활용 가능

---

## 🆚 경쟁자 분석 ("X vs Y")

### 주요 경쟁 서비스 비교

| 기능                   | 구절구절     | 브런치 | 네이버 블로그 | ChatGPT      | 맞춤법검사기 |
| ---------------------- | ------------ | ------ | ------------- | ------------ | ------------ |
| **AI 문학 변환**       | ✅ 전용 기능 | ❌     | ❌            | 🔶 수동 요청 | ❌           |
| **감정 기반 첨삭**     | ✅           | ❌     | ❌            | ❌           | ❌           |
| **목적별 스타일 제공** | ✅ 3가지     | ❌     | ❌            | 🔶 수동      | ❌           |
| **실시간 점수 시스템** | ✅           | ❌     | ❌            | ❌           | 🔶 기본적    |
| **소셜 & 랭킹 기능**   | ✅           | ✅     | 🔶 제한적     | ❌           | ❌           |
| **즉시 활용 가능**     | ✅ 원클릭    | ❌     | ❌            | 🔶 복사 필요 | 🔶 수정만    |
| **학습 효과**          | ✅ 체계적    | 🔶     | ❌            | 🔶           | 🔶 기본적    |
| **한국어 감정 특화**   | ✅           | 🔶     | ✅            | 🔶           | ✅           |
| **무료 사용**          | ✅ 베타      | ✅     | ✅            | 🔶 제한적    | ✅           |

### 🎯 왜 Verse by Verse를 선택해야 하는가?

1. **전용 AI 프리즘 엔진**: 문학 변환에 특화된 AI 프롬프트 엔지니어링으로 정확한 감정 변환
2. **원클릭 변환**: 복잡한 설정 없이 즉시 "짜증나"를 "무지개빛 문학"으로 변환
3. **목적별 맞춤 첨삭**: 학술용, 전문가용, SNS용 각각 다른 스타일 제공
4. **실시간 성장 확인**: AI 점수로 객관적인 글쓰기 실력 향상 추적
5. **즉시 활용 가능**: 첨삭된 글을 바로 SNS, 블로그, 과제 등에 활용
6. **재미있는 학습**: 딱딱한 공부가 아닌 놀이하듯 글쓰기 실력 향상

---

## 🖥️ 서비스 화면 구성

### 주요 화면 및 기능

#### 1. 🏠 메인 랜딩 페이지

- **프리즘 컨셉 소개**: "감정을 프리즘에 통과시켜 무지개빛 문학으로"
- **실제 변환 예시**: "짜증나" → "마음이 무거운 구름에 가려진 듯하다"
- **타겟별 어필**: 학습자, 직장인, 세련된 표현을 원하는 사람들
- **바로 변환해보기 CTA**: 원클릭으로 서비스 체험

#### 2. ✨ AI 첨삭 변환 화면

- **일상 표현 입력**: 자유로운 감정 표현 작성
- **감정 인식**: 😤 분노, 😄 기쁨, 😔 우울, 💕 설렘 등 자동 분류
- **목적별 스타일 선택**: 학술용, 전문가용, SNS용 첨삭 옵션
- **실시간 변환 결과**: 변환 전후 비교 뷰
- **점수 시스템**: 작문 실력, 창의성, 감정 표현도 실시간 평가

#### 3. 📊 점수 및 성장 추적

- **개인 성장 그래프**: 글쓰기 실력 향상 추이
- **세부 점수 분석**: 어휘력, 표현력, 창의성 등 영역별 점수
- **성취 배지**: 다양한 도전과제 완료 시 획득

#### 4. 🏆 랭킹 및 커뮤니티

- **전체 랭킹**: 다른 사용자들과 점수 비교
- **월간/주간 랭킹**: 꾸준한 동기부여를 위한 순위 시스템
- **댓글 & 반응**: 다른 사용자들과 소통 및 피드백

#### 5. 📱 SNS 공유 및 활용

- **원클릭 공유**: 첨삭된 글을 인스타그램, 블로그에 즉시 공유
- **공유 통계**: 좋아요, 댓글 수 등 반응 확인

#### 6. 🛠️ 관리자 기능

- **신고 관리 시스템**: 부적절한 콘텐츠 신고 및 처리
- **욕설 필터**: 자동 욕설 감지 및 차단
- **사용자 통계**: 서비스 사용 현황 및 분석

---

## 🚀 프로젝트 시작하기

### 📋 전제조건

- **Java 21** 이상
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

```bash
# 백엔드 테스트
cd versebyverse-backend
./gradlew test
```

## 🛠️ 기술 개요 및 구현 상세

### 📚 기술 스택

#### Backend

<img src="https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"> <img src="https://img.shields.io/badge/Spring_Boot-3.5.0-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white"> <img src="https://img.shields.io/badge/Spring_Security-6.3.0-6DB33F?style=for-the-badge&logo=spring-security&logoColor=white"> <img src="https://img.shields.io/badge/Spring_Data_JPA-3.5.0-6DB33F?style=for-the-badge&logo=spring&logoColor=white">

<img src="https://img.shields.io/badge/MySQL-8.4-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/Spring_AI-1.0.0-6DB33F?style=for-the-badge&logo=spring&logoColor=white"> <img src="https://img.shields.io/badge/Google_Vertex_AI-Gemini_2.0_Flash-4285F4?style=for-the-badge&logo=google-cloud&logoColor=white">

<img src="https://img.shields.io/badge/Gradle-8.x-02303A?style=for-the-badge&logo=gradle&logoColor=white"> <img src="https://img.shields.io/badge/JUnit5-5.x-25A162?style=for-the-badge&logo=junit5&logoColor=white"> <img src="https://img.shields.io/badge/Mockito-5.x-25A162?style=for-the-badge&logo=mockito&logoColor=white">

#### Infrastructure & DevOps

<img src="https://img.shields.io/badge/Docker-24.x-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/Docker_Compose-2.x-2496ED?style=for-the-badge&logo=docker&logoColor=white"> <img src="https://img.shields.io/badge/GitHub_Actions-2088FF?style=for-the-badge&logo=github-actions&logoColor=white">

#### Code Quality & Testing

<img src="https://img.shields.io/badge/JaCoCo-0.8.x-25A162?style=for-the-badge&logo=java&logoColor=white"> <img src="https://img.shields.io/badge/SonarQube-3.5.0-4E9BCD?style=for-the-badge&logo=sonarqube&logoColor=white"> <img src="https://img.shields.io/badge/Checkstyle-10.x-FFA500?style=for-the-badge&logo=checkstyle&logoColor=white">

#### Authentication & Cloud Services

<img src="https://img.shields.io/badge/OAuth2-Google-4285F4?style=for-the-badge&logo=google&logoColor=white"> <img src="https://img.shields.io/badge/AWS_S3-Spring_Cloud-FF9900?style=for-the-badge&logo=amazon-s3&logoColor=white">

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
```

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
