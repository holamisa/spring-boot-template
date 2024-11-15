package co.kr.bongjae.web.domain.auth.model;

import co.kr.bongjae.web.common.annotation.Password;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // toString + equals + hashCode + getter + setter + requiredArgsConstructor
@NoArgsConstructor // 인자 없는 생성자
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@Schema(title = "회원가입 요청 DTO") // Swagger 문서화
public class UserRegisterRequestDTO {

    @Schema(description = "사용자 이름")
    @NotBlank
    private String name;

    @Schema(description = "이메일 주소")
    @NotBlank
    @Email(message = "옳바른 메일 양식이 아닙니다.")
    private String email;

    @Schema(description = "비밀번호")
    @NotBlank
    @Password
    private String password;

    @Schema(description = "주소")
    @NotBlank
    private String address;
}
