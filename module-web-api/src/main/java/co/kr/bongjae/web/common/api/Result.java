package co.kr.bongjae.web.common.api;

import co.kr.bongjae.web.common.error.ErrorCode;
import co.kr.bongjae.web.common.error.ErrorCodeIf;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 응답 결과 상세를 담는 클래스
 */
@Data // toString + equals + hashCode + getter + setter + requiredArgsConstructor
@NoArgsConstructor // 인자 없는 생성자
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@Builder // 빌더 패턴 등록
@Schema(title = "API 응답 결과 상세") // Swagger 문서화
public class Result {

    // 2xx : Success Info
    // 4xx : Client Error
    // 5xx : Server Error
    @Schema(description = "결과 코드")
    private Integer resultCode;

    @Schema(description = "결과 메시지")
    private String resultMessage;

    @Schema(description = "결과 설명")
    private String resultDescription;

    /**
     * 성공 응답
     * @return 성공 응답
     */
    public static Result OK(){
        return Result.builder()
                .resultCode(ErrorCode.OK.getErrorCode())
                .resultMessage(ErrorCode.OK.getDescription())
                .resultDescription("성공")
                .build();
    }

    /**
     * 에러 응답
     * @param errorCodeIf 에러 코드
     * @return 에러 응답
     */
    public static Result ERROR(ErrorCodeIf errorCodeIf){
        return Result.builder()
                .resultCode(errorCodeIf.getErrorCode())
                .resultMessage(errorCodeIf.getDescription())
                .resultDescription("에러 발생")
                .build();
    }

    /**
     * 에러 응답
     * @param errorCodeIf 에러 코드
     * @param errorDescription 에러 설명
     * @return 에러 응답
     */
    public static Result ERROR(ErrorCodeIf errorCodeIf, String errorDescription){
        return Result.builder()
                .resultCode(errorCodeIf.getErrorCode())
                .resultMessage(errorCodeIf.getDescription())
                .resultDescription(errorDescription)
                .build();
    }

    /**
     * 에러 응답
     * 해당 방식은 비추함. 사용자에게 내부 에러 정보가 공유될 수 있음
     * @param errorCodeIf 에러 코드
     * @param throwable 에러 발생 객체
     * @return 에러 응답
     */
    public static Result ERROR(ErrorCodeIf errorCodeIf, Throwable throwable){
        return Result.builder()
                .resultCode(errorCodeIf.getErrorCode())
                .resultMessage(errorCodeIf.getDescription())
                .resultDescription(throwable.getLocalizedMessage())
                .build();
    }
}