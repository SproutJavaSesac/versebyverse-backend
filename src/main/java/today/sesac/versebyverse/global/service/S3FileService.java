package today.sesac.versebyverse.global.service;

import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

/**
 * S3 파일 업로드 및 관리를 담당하는 서비스입니다.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class S3FileService {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Value("${spring.cloud.aws.region.static}")
    private String region;

    /**
     * 이미지 파일을 S3에 업로드합니다.
     *
     * @param file      업로드할 이미지 파일
     * @param directory S3 내 저장할 디렉토리 (예: "posts", "profiles")
     * @return 업로드된 파일의 S3 URL
     */
    public String uploadImage(MultipartFile file, String directory) {

        try {
            // 파일 검증
            validateImageFile(file);

            // 고유한 파일명 생성
            String originalFilename = file.getOriginalFilename();
            String fileExtension = getFileExtension(originalFilename);
            String fileName = generateUniqueFileName(directory, fileExtension);

            // S3에 업로드
            String key = directory + "/" + fileName;

            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(key)
                    .contentType(file.getContentType())
                    .build();

            s3Client.putObject(putObjectRequest,
                    RequestBody.fromInputStream(file.getInputStream(), file.getSize()));

            // S3 URL 생성
            String s3Url = generateS3Url(bucketName, region, key);
            log.info("이미지 업로드 성공: {}", s3Url);

            return s3Url;

        } catch (IOException e) {
            log.error("이미지 파일 읽기 실패", e);
            throw new RuntimeException("이미지 파일을 읽을 수 없습니다.", e);
        } catch (S3Exception e) {
            log.error("S3 업로드 실패", e);
            throw new RuntimeException("이미지 업로드에 실패했습니다.", e);
        }
    }

    /**
     * S3에서 파일을 삭제합니다.
     *
     * @param s3Url 삭제할 파일의 S3 URL
     */
    public void deleteImage(String s3Url) {

        try {
            if (s3Url != null && s3Url.contains(bucketName)) {
                String key = extractKeyFromUrl(s3Url);
                DeleteObjectRequest deleteObjectRequest = DeleteObjectRequest.builder()
                        .bucket(bucketName)
                        .key(key)
                        .build();

                s3Client.deleteObject(deleteObjectRequest);
                log.info("이미지 삭제 성공: {}", key);
            }
        } catch (S3Exception e) {
            log.error("S3 파일 삭제 실패: {}", s3Url, e);
            // 삭제 실패 시에도 예외를 던지지 않고 로그만 남김
        }
    }

    /**
     * 이미지 파일 유효성을 검사합니다.
     */
    private void validateImageFile(MultipartFile file) {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("업로드할 이미지 파일이 없습니다.");
        }
//
//        String contentType = file.getContentType();
//        if (contentType == null || !contentType.startsWith("image/")) {
//            throw new IllegalArgumentException("이미지 파일만 업로드 가능합니다.");
//        }

        // 파일 크기 제한 (10MB)
        if (file.getSize() > 10 * 1024 * 1024) {
            throw new IllegalArgumentException("파일 크기는 10MB를 초과할 수 없습니다.");
        }
    }

    /**
     * 파일 확장자를 추출합니다.
     */
    private String getFileExtension(String filename) {

        if (filename == null || filename.lastIndexOf(".") == -1) {
            return "";
        }
        return filename.substring(filename.lastIndexOf("."));
    }

    /**
     * 고유한 파일명을 생성합니다.
     */
    private String generateUniqueFileName(String directory, String extension) {

        String timestamp = String.valueOf(System.currentTimeMillis());
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        return directory + "_" + timestamp + "_" + uuid + extension;
    }

    /**
     * S3 URL을 생성합니다.
     */
    private String generateS3Url(String bucket, String region, String key) {

        return String.format("https://%s.s3.%s.amazonaws.com/%s", bucket, region, key);
    }

    /**
     * S3 URL에서 키를 추출합니다.
     */
    private String extractKeyFromUrl(String s3Url) {

        String[] parts = s3Url.split("/");
        if (parts.length > 3) {
            StringBuilder key = new StringBuilder();
            for (int i = 3; i < parts.length; i++) {
                if (i > 3) {
                    key.append("/");
                }
                key.append(parts[i]);
            }
            return key.toString();
        }
        throw new IllegalArgumentException("유효하지 않은 S3 URL입니다: " + s3Url);
    }
}
