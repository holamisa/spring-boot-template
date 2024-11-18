package co.kr.bongjae.web.domain.auth.controller;

import co.kr.bongjae.web.common.annotation.Timer;
import co.kr.bongjae.web.common.api.ApiResult;
import co.kr.bongjae.web.domain.auth.business.AuthBusiness;
import co.kr.bongjae.web.domain.auth.model.LoginTokenDTO;
import co.kr.bongjae.web.domain.auth.model.UserRegisterRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

@RestController // Rest 컨트롤러임을 명시해줌
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
@RequestMapping("/open-api/auth") // API 주소 매핑
@Tag(name = "Auth OpenApi", description = "인증 Open API Document") // Swagger 문서화
public class AuthOpenApiController {

    /**
     * 인증 비즈니스
     */
    private final AuthBusiness authBusiness;

    /**
     * 로그인
     * @param authorizationHeader Authorization 헤더
     * @return 로그인 토큰
     */
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "로그인 프로세스")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = LoginTokenDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
//    @Timer
    public ApiResult<LoginTokenDTO> login(
            @Valid @Parameter(hidden = true)
            @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader) {

        var user = authBusiness.login(authorizationHeader);
        return ApiResult.OK(user);
    }

    /**
     * 회원가입
     * @param request 회원가입 요청
     * @return 로그인 토큰
     */
    @PostMapping("/register")
    @Operation(summary = "회원가입", description = "회원가입 프로세스")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = LoginTokenDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
//    @Timer
    public ApiResult<LoginTokenDTO> register(
            @Valid
            @RequestBody UserRegisterRequestDTO request) {

        var user = authBusiness.register(request);
        return ApiResult.OK(user);
    }
}
