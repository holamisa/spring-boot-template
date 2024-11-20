package co.kr.bongjae.db.model.file;

import co.kr.bongjae.db.model.BaseEntity;
import co.kr.bongjae.db.model.file.enums.FileStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * 파일 엔티티
 */
@Data // toString + equals + hashCode + getter + setter + requiredArgsConstructor
@SuperBuilder // 상속으로 통한 Builder이기에 필요 + 빌더 패턴 등록
@EqualsAndHashCode(callSuper = true) // 부모 필드 값도 비교
@NoArgsConstructor // 인자 없는 생성자
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@Entity // JPA Entity
@Table(name = "file") // 테이블 명
public class FileEntity extends BaseEntity {

    /**
     * 파일명
     */
    @Column(length = 100, nullable = false)
    private String name;

    /**
     * 원본 파일명
     */
    @Column(length = 100)
    private String originalName;

    /**
     * 파일 경로
     */
    @Column(length = 500, nullable = false)
    private String pathUrl;

    /**
     * 파일 크기
     */
    private Long size;

    /**
     * 파일 확장자
     */
    @Column(length = 20, nullable = false)
    private String extension;

    /**
     * 파일 상태
     */
    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private FileStatus status;

    /**
     * 등록 일
     */
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime registeredAt;
}
