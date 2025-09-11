package today.sesac.versebyverse.global.enums;

/**
 * S3 저장 경로를 정의하는 enum.
 */
public enum FileCategory {
    POST("posts"),
    PROFILE("profiles");

    private final String fileCategory;

    FileCategory(String fileCategory) {

        this.fileCategory = fileCategory;
    }

}
