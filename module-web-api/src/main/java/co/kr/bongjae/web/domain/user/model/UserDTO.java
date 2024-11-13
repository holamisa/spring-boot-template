package co.kr.bongjae.web.domain.user.model;

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
@Schema(title = "사용자 DTO") // Swagger 문서화
public class UserDTO {

    @Schema(description = "사용자 ID")
    private Long id;

    @Schema(description = "사용자 이름")
    private String name;

    @Schema(description = "사용자 이메일")
    private String email;

    @Schema(description = "사용자 주소")
    private String address;

    @Schema(description = "사용자 등록일")
    private LocalDateTime registeredAt;
}