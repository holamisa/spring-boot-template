package co.kr.bongjae.db.model.user;

import co.kr.bongjae.db.model.BaseEntity;
import co.kr.bongjae.db.model.user.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

/**
 * 사용자 엔티티
 */
@Data // toString + equals + hashCode + getter + setter + requiredArgsConstructor
@SuperBuilder // 상속으로 통한 Builder이기에 필요 + 빌더 패턴 등록
@EqualsAndHashCode(callSuper = true) // 부모 필드 값도 비교
@NoArgsConstructor // 인자 없는 생성자
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@Entity // JPA Entity
@Table(name = "user") // 테이블 명
public class UserEntity extends BaseEntity {

    /**
     * 이름
     */
    @Column(length = 50, nullable = false)
    private String name;

    /**
     * 이메일
     */
    @Column(length = 100, nullable = false)
    private String email;

    /**
     * 비밀번호
     */
    @Column(length = 100, nullable = false)
    private String password;

    /**
     * 상태
     */
    @Column(length = 50, nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    /**
     * 주소
     */
    @Column(length = 150, nullable = false)
    private String address;

    /**
     * 가입일
     */
    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime registeredAt;

    /**
     * 해지일
     */
    @Column(nullable = true, updatable = true)
    private LocalDateTime unregisteredAt;

    /**
     * 마지막 로그인 일시
     */
    private LocalDateTime lastLoginAt;
}
