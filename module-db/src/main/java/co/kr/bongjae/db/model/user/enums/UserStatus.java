package co.kr.bongjae.db.model.user.enums;

import lombok.AllArgsConstructor;

/**
 * 회원 상태 Enum
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
    UNREGISTERED("해지");

    private final String description;
}
