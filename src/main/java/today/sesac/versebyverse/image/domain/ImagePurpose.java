package today.sesac.versebyverse.image.domain;

/**
 * S3 이미지 업로드 할 때 사용되는 이미지 용도 정의.
 */
public enum ImagePurpose {
    POST("posts"),
    PROFILE("profiles");

    private final String fileCategory;

    ImagePurpose(String fileCategory) {

        this.fileCategory = fileCategory;
    }

}
