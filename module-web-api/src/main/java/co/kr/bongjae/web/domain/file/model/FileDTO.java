package co.kr.bongjae.web.domain.file.model;

import co.kr.bongjae.db.model.file.enums.FileStatus;
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
@Schema(title = "파일 DTO") // Swagger 문서화
public class FileDTO {

    @Schema(description = "파일 ID")
    private Long id;

    /**
     * 파일명
     */
    @Schema(description = "파일 명")
    private String name;

    /**
     * 원본 파일명
     */
    @Schema(description = "원본 파일 명")
    private String originalName;

    /**
     * 파일 경로
     */
    @Schema(description = "파일 경로")
    private String pathUrl;

    /**
     * 파일 크기
     */
    @Schema(description = "파일 크기")
    private Long size;

    /**
     * 파일 확장자
     */
    @Schema(description = "파일 확장자")
    private String extension;

    /**
     * 파일 상태
     */
    @Schema(description = "파일 상태")
    private FileStatus status;

    /**
     * 등록 일
     */
    @Schema(description = "파일 등록일")
    private LocalDateTime registeredAt;
}
