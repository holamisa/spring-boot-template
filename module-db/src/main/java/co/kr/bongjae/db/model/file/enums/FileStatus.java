package co.kr.bongjae.db.model.file.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
public enum FileStatus {
    /**
     * 등록
     */
    OK("등록"),

    /**
     * 삭제
     */
    DELETED("삭제"),

    /**
     * 알 수 없음
     */
    UNKNOWN("알 수 없음");

    private final String description;
}
