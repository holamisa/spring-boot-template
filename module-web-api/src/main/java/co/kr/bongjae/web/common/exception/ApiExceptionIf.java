package co.kr.bongjae.web.common.exception;

import co.kr.bongjae.web.common.error.ErrorCodeIf;

public interface ApiExceptionIf {

    ErrorCodeIf getErrorCodeIf();
    String getErrorDescription();
}