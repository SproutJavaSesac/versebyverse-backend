# 구절구절 - Verse by Verse

> AI가 당신의 일상을 문학으로 변환해주는 소셜 플랫폼

[![Version](https://img.shields.io/badge/version-0.1.0-blue.svg)](https://github.com/your-repo/verse-by-verse)
[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://openjdk.java.net/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.5.0-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

---

## 목차

## 팀원 소개

> **TEAM verseByVerse**

|      팀원       |                                                  강소연                                                  |                                                 박민석                                                  |                                                  이상준                                                  |                                                 황한나                                                  |
| :-------------: | :------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------------------------------: | :-----------------------------------------------------------------------------------------------------: |
|     프로필      | <img src="https://github.com/user-attachments/assets/7ff482f5-7697-482a-8197-baf6f7136a11" width="150" > | <img src="https://github.com/user-attachments/assets/dd92bb81-1911-4dda-a342-62ea41a1de52" width="150"> | <img src="https://github.com/user-attachments/assets/2acb0026-3185-4168-9ff3-d2febc47db7f" width="150" > | <img src="https://github.com/user-attachments/assets/41d6e961-29e4-4910-8e99-db72940c0a22" width="150"> |
|     GitHub      |                                  [@wosyh18](https://github.com/wosyh18)                                  |                               [@qkralstjr](https://github.com/qkralstjr)                                |                                 [@prac2317](https://github.com/prac2317)                                 |                              [@Sooamazing](https://github.com/Sooamazing)                               |
|  **주요 담당**  |                                  게시물 관리 시스템 설계, 반응하기 기능                                  |                                 Vertex AI 연동, 신고 처리, 비속어 관리                                  |                                   소셜 로그인, 회원 프로필, 성취 배지                                    |                      AI 첨삭 미리보기, 댓글, 랭킹 시스템 설계, 프로젝트 공통 설정                       |
|  **세부 기능**  |            게시글 작성/삭제/숨김 기능 구현, 게시물 페이지네이션 조회, 게시글에 반응 기능 구현            |                                AI 프롬프트 엔지니어링, 신고 시스템 설계                                 |                                   OAuth2 인증 시스템, 배지 시스템 설계                                   |                    미리보기 세션 관리, 3 계층 댓글, 일/주/월별 랭킹 자동 집계 시스템                    |
| **기술적 기여** |                                CI/CD, AWS 인프라 (C2, RDS, S3) 환경 구성                                 |                        **S3 이미지 업로드 시스템**<br/>파일 업로드 및 관리 구현                         |                         **인증 및 보안 시스템**<br/>OAuth2, 세션 기반 인증 관리                          |                 랭킹 점수 집계 스케줄링, **공통 응답 및 예외 체계 프로젝트 초기 설정**                  |
|    **공통**     |                   **프로젝트 관리 및 문서화**<br/>JavaDoc, Notion, GitHub Issues 활용                    |                         **테스트 자동화 및 품질 보증**<br/>JUnit, Mockito 활용                          |                         **RESTful API 문서화 및 관리**<br/>Notion, Postman 활용                          |              **코드 리뷰 및 품질 관리**<br/>GitHub Pull Request, Code Review 프로세스 활용              |

## 서비스 개요

### 서비스 소개 및 목적

**Verse by Verse**는 사용자가 작성한 일상의 글을 AI가 문학적으로 다듬어주는 혁신적인 소셜 플랫폼입니다.

**개발 기간**: 2025년 6월 ~ 2025년 8월 (3개월, 스프린트 3주 단위)

**특별한 기능들**:

1. **목적별 AI 첨삭**: 학술용, 전문가용, SNS용으로 각각 다른 톤앤매너
2. **실시간 점수 시스템**: 작문 실력, 창의성, 감정 표현도 점수 제공
3. **랭킹 & 소셜**: 다른 사용자들과 점수 비교 및 동기부여
4. **SNS 공유**: 첨삭된 글을 바로 인스타, 블로그에 활용 가능

### 어떤 문제를 해결할 수 있는가?

1. **표현력의 한계**: "짜증나", "기뻐", "슬퍼" 같은 단조로운 감정 표현을 풍부하게 변환
2. **글쓰기 실력 향상**: AI 첨삭을 통해 자연스럽게 어휘력과 표현력 향상
3. **창작 동기 부여**: 내 글이 문학작품으로 변화하는 재미와 성취감 제공
4. **소통의 질 개선**: SNS, 블로그에서 더 매력적이고 감각적인 글쓰기 가능
5. **학습 효과**: 수능, 논술, 글쓰기 실력에 직접적인 도움

### 어떤 사람들이 이 프로젝트를 사용하면 좋은가?

**학습자 & 수험생**

- 수능, 논술, 글쓰기 실력 향상이 필요한 학생들
- 문학 작품 연계로 국어 성적 향상을 목표로 하는 학습자들

**직장인 & 전문가**

- 업무, 소통에서 전문성을 보여주고 싶은 직장인들
- 개인 브랜딩을 위한 글쓰기 실력을 키우고 싶은 전문가들

**세련된 표현을 원하는 사람들**

- SNS, 일상 대화에서 매력적으로 소통하고 싶은 사람들
- 인스타그램, 블로그 게시글을 더 감각적으로 작성하고 싶은 사용자들

**글쓰기 연습을 원하는 사람들**

- 체계적인 피드백으로 글쓰기 실력을 늘리고 싶은 사람들
- 실제 활용 가능한 실용적 표현을 학습하고 싶은 사람들

---

## 서비스 화면 구성

### 1. 게시글, 게시판

- 담당자: [@wosyh18](https://github.com/wosyh18)
- **게시글 CRUD**: 게시글 작성(작성 시 원하는 , 삭제, 숨김처리를 할 수 있습니다.
- **게시글 조회**: 게시글을 댓글순, 반응순, 최신순으로 페이지네이션 조회가 가능합니다.
- **명령-조회 분리**: PostCommandController와 PostQueryController로 CQS 패턴 적용했습니다.
- **soft delete 적용**: 데이터 보존을 위해 삭제된 게시글도 db에 유지하여 복구가 가능합니다.
- **N+1 문제 해결**: 댓글 수 기준 정렬 시 한 번의 쿼리로 처리가 가능하게끔 구현했습니다.

### 2. 첨삭(미리보기)

![첨삭 미리보기 (GIF)](https://github.com/user-attachments/assets/16311031-f1a8-40fa-a3d9-2790200f6ad8)

- 담당자: [@Sooamazing](https://github.com/Sooamazing)
- 로직: **첨삭 요청** → 첨삭 세션 생성(세션/작업 ID 반환) → 변환 결과를 비교 뷰(원본/변환/하이라이트)로 확인 → **재첨삭** / **발행**
  - 첨삭 결과에 대한 피드백 수집 및 분석을 위해 세션을 생성해 반려/발행 여부 관리

### 3. 댓글

![댓글 UI](https://github.com/user-attachments/assets/907f0b34-b64b-4464-b89f-766cc928d218)

- 담당자: [@Sooamazing](https://github.com/Sooamazing)
- **댓글 생성 및 삭제**
  - 댓글 생성 시 댓글 간 관계를 명확히 하기 위해 parent_id, order, level 필드를 활용. 관리자 접근 시 상세 조회 가능.
  - soft-delete를 기본. level은 0(최상위)~2(대댓글 최대)로 제한.
  - order 계산: path를 이용한 계층적 정렬. 부모의 path를 기준으로 자식 그룹을 연속 배치.
- **댓글 (목록) 조회**
  - 페이지네이션, 정렬(작성순/반응순 등), 3단계(최대 level 2) 중첩 대댓글 지원. 
  - API 호출을 줄이기 위해 해당 댓글에 대한 반응 상태(myReaction)와 총합(reactionCount)을 함께 반환. 신고, 삭제 등의 UI도 고려해 설계.

### 4. 반응하기

- 담당자: [@wosyh18](https://github.com/wosyh18)
- **게시글과 댓글 반응하기**: 게시글과 댓글에 자신의 반응 등록, 동일 반응 누를 시 삭제, 자신의 반응이 존재한 상태에서
  다른 반응 누를 시 반응이 변경이 됩니다.
- **복합 유니크 제약조건**: 사용자가 같은 대상에 중복 반응을 방지 하기 위해 사용됩니다.

### 5. 랭킹 및 커뮤니티

![랭킹 UI (GIF)](https://github.com/user-attachments/assets/cd0e7484-fd9b-42cb-8762-62b1228939c1)

- 담당자: [@Sooamazing](https://github.com/Sooamazing)
- 기간(일간/주간/월간)과 카테고리에 따른 랭킹 집계, 순위 변동, 상위 N 백엔드 제공.
- **랭킹 집계**: Spring @Scheduled로 정기적인 실행: 실시간 계산을 피하기 위해 배치/스케줄 방식 사용. 매일 00:00에 실행, 10분으로 가정하고, GET 요청에 대해서 해당 일정에 대해 00:10 후에 결과가 반영되도록 설계.
  - 집계 로직: SQL 집계 쿼리로 category_value 산출 후 RANK() 윈도우 함수로 순위 계산. 이전 랭킹 비교를 위해 이전 기간 결과를 조회하여 rankChange 계산.

### 6. 관리자 기능

<img width="45%" height="250" alt="Image" src="https://github.com/user-attachments/assets/fd11e051-ba68-4371-bca2-8a0e2b69c2c3" />
<img width="45%" height="250" alt="Image" src="https://github.com/user-attachments/assets/19176ab7-6405-48d4-9f6d-de43eff28804" />

- 담당자: [@qkralstjr](https://github.com/qkralstjr)
- **신고 관리**: 신고된 컨텐츠 목록 필터링&페이지네이션 조회, 신고 처리(승인/거절)
- **비속어 관리**: 비속어 등록, 조회, 수정, 삭제 구현

### 7. 배지 시스템

- 담당자: [@prac2317](https://github.com/prac2317)
- **배지 시스템**: 반응하기 횟수, 게시글 수, 댓글 수 등이 특정 조건을 만족할 때 배지를 발급합니다.
- **비동기 처리**: 중요한 기능(회원가입, 게시글 작성 등)과 부수적인 기능(배지 발급)을 구분하여 처리할 수 있도록 배지는 비동기(@Async 활용)적으로 발급하며,
  각 로직은 별도의 트랜잭션에서 실행됩니다.
- **이벤트 기반**: @EventListener를 활용하여 핵심 비즈니스 로직과 배지 발급 로직을 구분합니다.

### 8. 회원 관리 및 프로필 기능

- 담당자: [@prac2317](https://github.com/prac2317)
- **회원 정보 관리**: 마이페이지에서 자신의 프로필 정보를 수정하고, 계정을 관리(회원 탈퇴)할 수 있습니다.
- **프로필 조회**: 다른 회원의 프로필을 방문하여 작성한 게시글 등 활동 내역을 모아볼 수 있습니다,
- **소셜 연동 관리**: 회원 탈퇴 시, 서비스와 연결된 소셜 계정의 연동을 안전하게 해제합니다.

### 9. 인증 / 인가

- 담당자: [@prac2317](https://github.com/prac2317)
- **로그인**: 소셜 로그인을 구현하여 별도의 회원가입이나 로그인 과정 없이 카카오, 구글 계정을 통해 로그인합니다.
- **세션 기반**: 서버는 세션 기반으로 사용자의 로그인 상태를 관리합니다.
- Spring Security를 사용하여 필터 기반으로, 소셜 로그인은 OAuth2를 기반으로 구현되었습니다.

---

## 기술 개요 및 구현 상세

### 기술 스택

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

### 시스템 아키텍처

![Deployment Architecture](https://github.com/user-attachments/assets/cdf25e69-76f2-4e49-8ffd-95c5d7750720)

> **시스템 아키텍처 개요**: 사용자가 Vercel을 통해 Next.js 프론트엔드에 접근하고, AWS EC2에서 실행되는 Spring Boot 백엔드와 통신합니다.
> Vertex
> AI를 통한 AI 기능, MySQL 데이터베이스, S3 파일 저장소가 통합된 클라우드 기반 아키텍처입니다.

### ERD (Entity Relationship Diagram)

![Image](https://github.com/user-attachments/assets/907e11a5-d6c2-4c71-a8d5-e328c4b293c2)

### API 설계

[API 설계](https://www.notion.so/hannanana/API-20d33717f91380739342c33da2c7b92e?source=copy_link)

---

## 파일 구조

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

## Testing

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

## 개발 규칙

### 커밋 컨벤션

커밋 이력을 쉽게 파악할 수 있도록 다음 컨벤션을 따릅니다.

|   **Type**   | **설명**                                                                                      |
|:------------:|---------------------------------------------------------------------------------------------|
|   **Feat**   | 새로운 기능 추가                                                                                   |
|   **Fix**    | 버그 수정                                                                                       |
| **Refactor** | 코드 리팩토링                                                                                     |
|   **Test**   | 테스트 코드, 리팩토링 테스트 코드 추가                                                                      |
|   **Docs**   | 문서 작성 및 수정                                                                                  |
|  **Chore**   | 코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우<br/>파일/폴더 이름 수정하거나 옮기는 작업<br/>파일 삭제<br/>필요한 주석 추가 및 수정<br/>기타 |

**Example:**

```
[#issue]Type(obj): subject # 이슈 번호를 포함한 요약(무엇을(O), 왜(O) 변경했는지를 설명)

body # 선택, 상세 내용 적기 - 어떻게보다(X) 무엇을(O), 왜(O) 변경했는지를 설명

```

### 브랜치 전략

프로젝트는 Github-flow를 기반으로 합니다.

- **브랜치 종류**
    - **main**: 프로덕션 배포
    - **develop**: 개발 통합
    - **feature**: 기능 개발
- **브랜치 네이밍 컨벤션**
    - **형식**: {이슈번호}-{이슈라벨}/{기능명}
    - **예시**: 13-feature/member-signup

### 코드 리뷰(Pull Request)

모든 코드는 Pull Request를 통해 develop 브랜치에 병합하는 것을 원칙으로 하며, Pull Request 제출 시 코드 리뷰를 통해 코드 품질을 유지합니다.

- **중요도 구분**: 리뷰어는 P1, P2, P3로 리뷰의 중요도를 구분하여 팀원이 구분할 수 있도록 합니다.
- **템플릿**: PR 생성시 요약, 검증 방법, 체크리스트 등을 담은 [템플릿](.github/pull_request_template.md)를 사용하여 리뷰어가 변경 내용을
  쉽게 이해하도록 합니다.
- **Pull Request 규칙**
    - PR을 생성하기 전 draft로 만들고, 체크리스트에서 제시한 조건을 준수했는지 확인 후 정식 리뷰를 요청합니다.
    - 리뷰어 승인은 최소 2명에게 승인받는 방식에서 신속한 개발을 위해 1명을 지정하여 승인받는 방식으로 변경하여 진행했습니다.
    - 모든 테스트가 통과해야 머지가 가능합니다.

### 코드 스타일 및 코드 품질 관리

- **코드 스타일**: Google Java Style Guide를 기반으로, IntelliJ IDEA의 Checkstyle 플러그인과 SonarLint를 통한 실시간 코드 검증
- **코드 품질 관리**: SonarQube를 활용한 코드 품질 관리
- **기타 컨벤션**
    - **페이지네이션 표준화**: Spring Data JPA의 페이지네이션을 기준으로 페이지네이션 클래스(PaginationDto) 구현 및 활용
    - **예외 처리 체계**: 표준화된 예외 처리를 위한 공통 인터페이스(IErrorCode) 및 추상 클래스(AbstractBaseException) 구현 및 활용
    - **공통 응답 체계**: ApiResponse 클래스로 통일된 REST API 응답

---

## 팀 규칙

### 협업 도구

- **Slack**: 팀의 주요 소통 채널입니다. 매일 그 날의 스레드를 만들어 대화를 주고 받습니다.
- **Zep**: 데일리 스크럼, 코어 타임 등 실시간 소통이 필요한 경우 Zep에서 소통합니다.
- **Notion**: 팀의 모든 문서를 기록하고 관리합니다. 회의록, 기획서, 팀 규칙, 트러블 슈팅 기록 등을 체계적으로 기록합니다.

### 데일리 스크럼

- 주 3회 온라인(월, 수, 금) 11시, 주 3회 오프라인(화, 목, 토) 만나서 스크럼을 진행합니다.
- **운영 방식**
    - **컨디션 점수**: 각자의 컨디션을 1~10점으로 기록해 팀원의 상태를 확인, 서로의 컨디션을 존중하며 업무를 진행합니다.
    - **업무 공유**: 이전까지 한 일과 오늘 해야 할 일을 팀과 공유합니다.
    - **공지**: 팀이 논의해야 할 사항이 있으면 공지에 적고 공유합니다.
    - **기타**: 개인 일정이나 전달 사항이 있을 경우 비고에 적고 공유합니다.

### 집중 시간(코어 타임)

필요할 때 즉각적인 소통이 가능한 환경을 조성하기 위해 집중 시간을 운영합니다.

- **시간**: 월, 수, 금 오후 13시 ~ 18시
- **장소**: Zep에서 온라인으로 실시간 소통

### 프로젝트 관리

- **스프린트**: 3주 단위의 스프린트로, 총 4번의 스프린트 진행
- **이슈 관리**: 모든 작업은 Github Issue를 기본 단위로 관리
    - 유형([기능 개발](/.github/ISSUE_TEMPLATE/feature_request.md), [버그 리포트](/.github/ISSUE_TEMPLATE/bug_report.md))별로 템플릿 작성
    - 라벨, 타입, 마일스톤을 통한 체계적 분류
- **프로젝트 추적**: Github project를 사용해 개발 진행 상황 시각화 및 추적
    - PR 기간, 이슈 기간을 시간별로 시각화

---

**⭐ 이 프로젝트가 마음에 드신다면 Star를 눌러주세요!**

_마지막 업데이트: 2025년 9월_
