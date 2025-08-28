package today.sesac.versebyverse.member.entity;

import com.fasterxml.jackson.annotation.JsonValue;

/**
 * 사용자의 권한.
 */
public enum RoleType {

    ROLE_USER, ROLE_ADMIN;

    @JsonValue
    public String getValue() {

        return this.name().replace("ROLE_", "");
    }

}