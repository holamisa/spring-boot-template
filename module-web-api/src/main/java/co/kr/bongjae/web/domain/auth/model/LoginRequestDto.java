package co.kr.bongjae.web.domain.auth.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(title = "로그인 요청 DTO") // Swagger 문서화
public record LoginRequestDto(
        @NotBlank(message = "이메일은 필수 입니다.")
        @Email
        @Schema(description = "이메일")
        String email,

        @NotBlank(message = "비밀번호는 필수 입니다.")
        @Schema(description = "비밀번호")
        String password) {}