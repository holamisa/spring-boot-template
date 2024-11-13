package co.kr.bongjae.web.exception;

import co.kr.bongjae.web.common.api.ApiResult;
import co.kr.bongjae.web.common.error.ErrorCode;
import co.kr.bongjae.web.common.exception.ApiException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * API 예외 처리 핸들러
 */
@RestControllerAdvice
@Slf4j
@Order(value = Integer.MIN_VALUE) // 최우선 실행되도록
public class ApiExceptionHandler {

    /**
     * ApiException 처리
     * @param apiException
     * @return
     */
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<ApiResult<Object>> apiException(
            ApiException apiException
    ){
        log.error("", apiException);

        var errorCode = apiException.getErrorCodeIf();

        return ResponseEntity
                .status(errorCode.getHttpStatusCode())
                .body(ApiResult.ERROR(errorCode, apiException.getErrorDescription()));
    }

    /**
     * MethodArgumentNotValidException 처리
     * @param exception
     * @return
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResult<Object>> validationException(
            MethodArgumentNotValidException exception
    ){
        log.error("", exception);

        var errorMessageList = exception.getFieldErrors().stream()
                .map(x -> {
                    var format = "%s : { %s } 은 %s";
                    return String.format(format, x.getField(), x.getRejectedValue(), x.getDefaultMessage());
                })
                .collect(Collectors.toList());

        var message = String.join(", ", errorMessageList);

        return ResponseEntity
                .status(ErrorCode.BAD_REQUEST.getErrorCode())
                .body(ApiResult.ERROR(ErrorCode.BAD_REQUEST, message));
    }
}
