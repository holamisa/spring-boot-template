package co.kr.bongjae.web.domain.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data // toString + equals + hashCode + getter + setter + requiredArgsConstructor
@NoArgsConstructor // 인자 없는 생성자
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@Builder // 빌더 패턴 등록
@Schema(title = "로그인 토큰 DTO") // Swagger 문서화
public class LoginTokenDTO {

    @Schema(description = "Access Token")
    private String accessToken;

    @Schema(description = "Access Token 만료 일시")
    private LocalDateTime accessTokenExpiredAt;

    @Schema(description = "Refresh Token")
    private String refreshToken;

    @Schema(description = "Refresh Token 만료 일시")
    private LocalDateTime refreshTokenExpiredAt;
}
