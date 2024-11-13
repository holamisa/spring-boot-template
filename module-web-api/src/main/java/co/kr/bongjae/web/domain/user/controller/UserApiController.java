package co.kr.bongjae.web.domain.user.controller;

import co.kr.bongjae.web.common.api.ApiResult;
import co.kr.bongjae.web.domain.user.business.UserBusiness;
import co.kr.bongjae.web.domain.user.model.UserDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // Rest 컨트롤러임을 명시해줌
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
@RequestMapping("/api/user") // API 주소 매핑
@Tag(name = "UserApi", description = "사용자 API Document") // Swagger 문서화
public class UserApiController {

    /**
     * 사용자 비즈니스
     */
    private final UserBusiness userBusiness;

    /**
     * 사용자 조회
     * @param id 사용자 ID
     * @return 사용자 DTO
     */
    @GetMapping()
    @Operation(summary = "사용자 조회", description = "ID로 사용자 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    public ApiResult<UserDTO> getUserById(
            @Parameter(name = "id", description = "사용자 ID", required = true)
            @RequestParam(required = true) Long id) {

        var user = userBusiness.getUserById(id);
        return ApiResult.OK(user);
    }

    @GetMapping("/v2")
    @Operation(summary = "사용자 조회", description = "ID로 사용자 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDTO.class))),
            @ApiResponse(responseCode = "404", description = "Not Found"),
    })
    public ApiResult<UserDTO> getUserByIdV2(
            @Parameter(name = "id", description = "사용자 ID", required = true)
            @RequestParam(required = true) Long id) {

        var user = userBusiness.getUserByIdV2(id);
        return ApiResult.OK(user);
    }

    @GetMapping("/all")
    @Operation(summary = "모든 사용자 조회", description = "사용자 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Success", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = UserDTO.class))),
    })
    public ApiResult<List<UserDTO>> getAllUser(){
        var users = userBusiness.getAllUser();
        return ApiResult.OK(users);
    }
}
