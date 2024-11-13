package co.kr.bongjae.web.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * API 결과 코드
 */
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@Getter
public enum ErrorCode implements ErrorCodeIf {

    OK(HttpStatus.OK.value(), 200, "성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), 400, "잘못된 요청"),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), 500, "서버 에러"),
    NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR.value(), 512, "Null Point");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}