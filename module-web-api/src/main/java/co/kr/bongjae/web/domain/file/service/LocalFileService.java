package co.kr.bongjae.web.domain.file.service;

import co.kr.bongjae.db.jpa.FileRepository;
import co.kr.bongjae.db.model.file.FileEntity;
import co.kr.bongjae.db.model.file.enums.FileStatus;
import co.kr.bongjae.web.common.error.FileErrorCode;
import co.kr.bongjae.web.common.exception.ApiException;
import co.kr.bongjae.web.domain.file.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

@Slf4j // 로깅
@Service // 서비스 로직임을 명시해줌
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
public class LocalFileService {

    @Value("${upload.directory}")
    private String uploadDirectory;

    /**
     * 파일 레파지토리
     */
    private final FileRepository fileRepository;

    /**
     * 파일 저장
     *
     * @param fileEntity 파일 엔티티
     * @return 저장된 파일 엔티티
     */
    public FileEntity saveFile(FileEntity fileEntity) {
        return fileRepository.save(fileEntity);
    }

    public FileEntity uploadFile(MultipartFile file, String uploadPath) {
        // 1. 파일 검증
        if (file.isEmpty()) {
            throw new ApiException(FileErrorCode.EMPTY_FILE);
        }

        if (!FileUtils.isExtensionAllowed(file)) {
            throw new ApiException(FileErrorCode.INVALID_FILE);
        }

        // 2. 파일 경로 확인
        Path filePath = null;
        try {
            var uploadDirectoryPath = Path.of(uploadDirectory).resolve(uploadPath);
            filePath = FileUtils.ensureDirectoryExists(uploadDirectoryPath);
        } catch (IOException e) {
            throw new ApiException(FileErrorCode.EXCEPTION_DIRECTORY);
        }

        var fileExtension = FileUtils.getFileExtension(file);
        var newFileName = FileUtils.generateUniqueFileName() + fileExtension;

        // 3. 파일 저장
        try {
            FileUtils.saveFile(filePath, newFileName, file);
        } catch (IOException e) {
            throw new ApiException(FileErrorCode.UPLOAD_FAILURE);
        }

        return saveFile(FileEntity.builder()
                .name(newFileName)
                .originalName(file.getOriginalFilename())
                .pathUrl(filePath.toString())
                .size(file.getSize())
                .extension(fileExtension)
                .status(FileStatus.OK)
                .registeredAt(LocalDateTime.now())
                .build());
    }
}
