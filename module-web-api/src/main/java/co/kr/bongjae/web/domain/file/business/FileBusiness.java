package co.kr.bongjae.web.domain.file.business;

import co.kr.bongjae.web.common.annotation.Business;
import co.kr.bongjae.web.domain.file.model.FileDTO;
import co.kr.bongjae.web.domain.file.service.LocalFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Business // 비즈니스 로직임을 명시해줌
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
public class FileBusiness {

    /**
     * 파일 서비스
     */
    private final LocalFileService localFileService;

    /**
     * 파일 컨버터
     */
    private final FileConverter fileConverter;

    /**
     * 파일 업로드
     *
     * @param file 파일
     * @return 파일 DTO
     */
    public FileDTO uploadFile(MultipartFile file) {
        var uploadPath = "images/me";
        var fileEntity = localFileService.uploadFile(file, uploadPath);

        return fileConverter.toDto(fileEntity);
    }
}
