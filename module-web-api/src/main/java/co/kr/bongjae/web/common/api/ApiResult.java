package co.kr.bongjae.web.common.api;

import co.kr.bongjae.web.common.error.ErrorCodeIf;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * API 응답 결과를 담는 클래스
 * @param <T> 응답 데이터 타입
 */
@Data // toString + equals + hashCode + getter + setter + requiredArgsConstructor
@NoArgsConstructor // 인자 없는 생성자
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@Builder // 빌더 패턴 등록
public class ApiResult<T> {

    /**
     * 응답 결과
     */
    private Result result;

    /**
     * 응답 데이터
     */
    @Valid
    private T body;

    /**
     * 성공 응답
     * @param data 응답 데이터
     * @return 성공 응답
     * @param <T> 응답 데이터 타입
     */
    public static <T> ApiResult<T> OK(T data){
        return ApiResult.<T>builder()
                .result(Result.OK())
                .body(data)
                .build();
    }

    /**
     * 에러 응답
     * @param result 응답 결과
     * @return 에러 응답
     */
    public static ApiResult<Object> ERROR(Result result){
        return ApiResult.<Object>builder()
                .result(result)
                .build();
    }

    /**
     * 에러 응답
     * @param errorCodeIf 에러 코드
     * @return 에러 응답
     */
    public static ApiResult<Object> ERROR(ErrorCodeIf errorCodeIf){
        return ApiResult.<Object>builder()
                .result(Result.ERROR(errorCodeIf))
                .build();
    }

    /**
     * 에러 응답
     * @param errorCodeIf 에러 코드
     * @param errorDescription 에러 설명
     * @return 에러 응답
     */
    public static ApiResult<Object> ERROR(ErrorCodeIf errorCodeIf, String errorDescription){
        return ApiResult.<Object>builder()
                .result(Result.ERROR(errorCodeIf, errorDescription))
                .build();
    }

    /**
     * 에러 응답
     * 해당 방식은 비추함. 사용자에게 내부 에러 정보가 공유될 수 있음
     * @param errorCodeIf 에러 코드
     * @param throwable 에러 발생 객체
     * @return 에러 응답
     */
    public static ApiResult<Object> ERROR(ErrorCodeIf errorCodeIf, Throwable throwable){
        return ApiResult.<Object>builder()
                .result(Result.ERROR(errorCodeIf, throwable))
                .build();
    }
}