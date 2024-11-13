package co.kr.bongjae.web.common.exception;

import co.kr.bongjae.web.common.error.ErrorCodeIf;
import lombok.Getter;

/**
 * API 예외 처리를 위한 클래스
 */
@Getter
public class ApiException extends RuntimeException implements ApiExceptionIf{
    private final ErrorCodeIf errorCodeIf;
    private final String errorDescription;

    public ApiException(ErrorCodeIf errorCodeIf){

        super(errorCodeIf.getDescription());
        this.errorCodeIf = errorCodeIf;
        this.errorDescription = errorCodeIf.getDescription();
    }

    public ApiException(ErrorCodeIf errorCodeIf, String errorDescription){

        super(errorDescription);
        this.errorCodeIf = errorCodeIf;
        this.errorDescription = errorDescription;
    }

    public ApiException(ErrorCodeIf errorCodeIf, Throwable throwable){

        super(throwable);
        this.errorCodeIf = errorCodeIf;
        this.errorDescription = errorCodeIf.getDescription();
    }

    public ApiException(ErrorCodeIf errorCodeIf, Throwable throwable, String errorDescription){

        super(throwable);
        this.errorCodeIf = errorCodeIf;
        this.errorDescription = errorDescription;
    }
}
