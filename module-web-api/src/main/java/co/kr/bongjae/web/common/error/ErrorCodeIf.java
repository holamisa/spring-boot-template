package co.kr.bongjae.web.common.error;

public interface ErrorCodeIf {

    Integer getHttpStatusCode();
    Integer getErrorCode();
    String getDescription();
}
