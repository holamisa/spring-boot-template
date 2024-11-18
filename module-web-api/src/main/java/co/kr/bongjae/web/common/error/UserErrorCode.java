package co.kr.bongjae.web.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 사용자 관련 API 결과 코드
 */
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@Getter
public enum UserErrorCode implements ErrorCodeIf {

    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), 1404, "존재하지 않는 사용자입니다."),
    PASSWORD_INCORRECT(HttpStatus.BAD_REQUEST.value(), 1405, "비밀번호가 일치하지 않습니다."),
    USER_DUPLICATE(HttpStatus.BAD_REQUEST.value(), 1406, "이미 가입된 계정이 존재합니다.");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}