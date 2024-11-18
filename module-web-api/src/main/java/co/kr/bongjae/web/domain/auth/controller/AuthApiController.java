package co.kr.bongjae.web.domain.auth.controller;

import co.kr.bongjae.web.common.annotation.UserSession;
import co.kr.bongjae.web.common.api.ApiResult;
import co.kr.bongjae.web.domain.auth.business.AuthBusiness;
import co.kr.bongjae.web.domain.auth.model.LoginTokenDTO;
import co.kr.bongjae.web.domain.user.model.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController // Rest 컨트롤러임을 명시해줌
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
@RequestMapping("/api/auth") // API 주소 매핑
@Tag(name = "AuthApi", description = "인증 API Document") // Swagger 문서화
public class AuthApiController {

    /**
     * 인증 비즈니스
     */
    private final AuthBusiness authBusiness;

    @DeleteMapping("/unregister")
    @Operation(summary = "회원탈퇴", description = "회원탈퇴 프로세스")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = LoginTokenDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
//    @Timer
    public ApiResult<Object> unRegister(
            @Valid @Parameter(hidden = true)
            @UserSession UserDTO user) {

        authBusiness.unRegister(user.getId());
        return ApiResult.OK();
    }
}
