package co.kr.bongjae.web.domain.file.controller;

import co.kr.bongjae.web.common.api.ApiResult;
import co.kr.bongjae.web.domain.file.business.FileBusiness;
import co.kr.bongjae.web.domain.file.model.FileDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController // Rest 컨트롤러임을 명시해줌
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
@RequestMapping("/open-api/file") // API 주소 매핑
@Tag(name = "File OpenApi", description = "파일 Open API Document") // Swagger 문서화
public class FileOpenApiController {

    /**
     * 파일 비즈니스
     */
    private final FileBusiness fileBusiness;

    /**
     * 이미지 파일 업로드
     *
     * @param file 이미지 파일
     * @return 파일 DTO
     */
    @PostMapping("/upload")
    public ApiResult<FileDTO> uploadImageToFileSystem(
            @RequestParam("image") MultipartFile file) {
        var fileDto = fileBusiness.uploadFile(file);
        return ApiResult.OK(fileDto);
    }
}
