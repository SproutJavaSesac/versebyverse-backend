# 이미지 업로드 기능 구현 가이드

## 개요

게시글 등록 시 이미지 파일을 S3에 업로드하고 관리하는 기능을 구현했습니다.

## 구현된 기능

### 1. S3 파일 업로드 서비스 (`S3FileService`)

- 이미지 파일을 S3에 업로드
- 파일 유효성 검사 (이미지 파일만 허용, 10MB 제한)
- 고유한 파일명 생성 (타임스탬프 + UUID)
- S3에서 파일 삭제

### 2. 게시글 이미지 업로드

- `PostCreateRequestDto`에 `MultipartFile imageFile` 필드 추가
- 게시글 생성 시 이미지 파일을 S3에 업로드
- 업로드된 이미지 URL을 게시글 엔티티에 저장

### 3. 이미지 자동 삭제

- 게시글 삭제 시 S3의 이미지도 자동으로 삭제

## 설정 방법

### 1. 환경 변수 설정

`.env` 파일에 다음 환경 변수를 추가하세요:

```bash
# AWS S3 설정
S3_ACCESS_KEY=your_access_key
S3_SECRET_ACCESS_KEY=your_secret_key
S3_BUCKET_NAME=your_bucket_name
```

### 2. application.yml 설정

```yaml
spring:
  cloud:
    aws:
      credentials:
        access-key: ${S3_ACCESS_KEY}
        secret-key: ${S3_SECRET_ACCESS_KEY}
      region:
        static: ap-northeast-2

aws:
  s3:
    bucket: ${S3_BUCKET_NAME}
    region: ap-northeast-2
```

## 사용법

### 1. 게시글 작성 (이미지 포함)

```http
POST /api/v1/posts
Content-Type: multipart/form-data

title: 게시글 제목
conceptType: POETRY
emotionType: JOY
content: 게시글 내용
imageFile: [이미지 파일]
```

### 2. 이미지 파일 요구사항

- 파일 형식: 이미지 파일만 허용 (JPEG, PNG, GIF 등)
- 파일 크기: 최대 10MB
- 필수 여부: 선택사항

## 파일 구조

```
src/main/java/today/sesac/versebyverse/
├── global/
│   ├── config/
│   │   └── S3Config.java          # S3 설정
│   ├── exception/
│   │   └── FileUploadException.java # 파일 업로드 예외
│   └── service/
│       └── S3FileService.java     # S3 파일 업로드 서비스
└── post/
    ├── controller/
    │   └── PostCommandController.java # 게시글 명령 컨트롤러
    ├── dto/
    │   └── request/
    │       └── PostCreateRequestDto.java # 게시글 생성 요청 DTO
    └── service/
        └── PostCommandService.java # 게시글 명령 서비스
```

## 보안 고려사항

1. **파일 형식 검증**: 이미지 파일만 업로드 허용
2. **파일 크기 제한**: 10MB 제한으로 서버 리소스 보호
3. **고유 파일명**: UUID를 사용하여 파일명 충돌 방지
4. **에러 처리**: 업로드 실패 시 적절한 예외 처리

## 테스트

테스트 환경에서는 Mock S3Client를 사용하여 실제 S3 연결 없이 테스트가 가능합니다.

```bash
./gradlew test
```

## 트러블슈팅

### 1. S3 연결 오류

- AWS 자격 증명이 올바른지 확인
- S3 버킷이 존재하고 접근 권한이 있는지 확인
- 리전 설정이 올바른지 확인

### 2. 파일 업로드 실패

- 파일 형식이 이미지인지 확인
- 파일 크기가 10MB 이하인지 확인
- 네트워크 연결 상태 확인

### 3. 이미지 삭제 실패

- S3 URL이 올바른지 확인
- S3 버킷에 삭제 권한이 있는지 확인

## 향후 개선 사항

1. **이미지 리사이징**: 업로드된 이미지 자동 리사이징
2. **썸네일 생성**: 게시글 목록용 썸네일 자동 생성
3. **CDN 연동**: CloudFront 등 CDN을 통한 이미지 전송 최적화
4. **이미지 압축**: 업로드 시 이미지 품질 최적화 