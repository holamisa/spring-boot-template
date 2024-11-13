package co.kr.bongjae.db.model.user.enums;

import lombok.AllArgsConstructor;

/**
 * 사용자 상태 Enum
 */
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
public enum UserStatus {

    /**
     * 등록
     */
    REGISTERED("등록"),

    /**
     * 해지
     */
    UNREGISTERED("해지"),

    /**
     * 알 수 없음
     */
    UNKNOWN("알 수 없음");

    private final String description;
}
