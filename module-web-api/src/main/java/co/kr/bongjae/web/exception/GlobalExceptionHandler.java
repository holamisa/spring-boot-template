package co.kr.bongjae.web.exception;

import co.kr.bongjae.web.common.api.ApiResult;
import co.kr.bongjae.web.common.error.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 글로벌 예외 처리 핸들러
 */
@RestControllerAdvice // 모든 컨트롤러에 적용
@Slf4j // 로깅
@Order(value = Integer.MAX_VALUE) // 최후에 실행되도록
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ApiResult<Object>> exception(
            Exception exception
    ){
        log.error("", exception);

        return ResponseEntity
                .status(ErrorCode.SERVER_ERROR.getErrorCode())
                .body(ApiResult.ERROR(ErrorCode.SERVER_ERROR));
    }
}