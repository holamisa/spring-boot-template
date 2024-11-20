package co.kr.bongjae.web.common.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@Getter
public enum FileErrorCode implements ErrorCodeIf {

    INVALID_FILE(HttpStatus.BAD_REQUEST.value(), 3000, "올바르지 않은 파일."),
    INVALID_SIZE(HttpStatus.BAD_REQUEST.value(), 3001, "올바르지 않은 파일 크기."),
    EXCEPTION_FILE(HttpStatus.BAD_REQUEST.value(), 3002, "파일 알 수 없는 에러."),
    FILE_NOT_FOUND(HttpStatus.NOT_FOUND.value(), 3003, "파일 찾을 수 없음."),
    UPLOAD_FAILURE(HttpStatus.BAD_REQUEST.value(), 3004, "파일 업로드 실패."),
    DOWNLOAD_FAILURE(HttpStatus.BAD_REQUEST.value(), 3005, "파일 다운로드 실패."),
    DELETE_FAILURE(HttpStatus.BAD_REQUEST.value(), 3006, "파일 삭제 실패."),
    EXCEPTION_DIRECTORY(HttpStatus.BAD_REQUEST.value(), 3007, "디렉토리 알 수 없는 에러."),
    EMPTY_FILE(HttpStatus.BAD_REQUEST.value(), 3008, "빈 파일.");

    private final Integer httpStatusCode;
    private final Integer errorCode;
    private final String description;
}
