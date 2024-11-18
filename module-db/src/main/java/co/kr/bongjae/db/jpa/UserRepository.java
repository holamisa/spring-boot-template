package co.kr.bongjae.db.jpa;

import co.kr.bongjae.db.model.user.UserEntity;
import co.kr.bongjae.db.model.user.enums.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 * 사용자 레파지토리
 * JPA
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * 사용자 ID와 상태로 사용자 정보 조회
     * select * from `user` where id = ? and status = ?
     * @param userId 사용자 ID
     * @param status 사용자 상태
     * @return 사용자 정보
     */
    Optional<UserEntity> findByIdAndStatus(Long userId, UserStatus status);

    /**
     * 사용자 이메일과 상태로 사용자 정보 조회
     * select * from `user` where email = ? and status = ? limit 1
     * @param email 사용자 이메일
     * @param status 사용자 상태
     * @return 사용자 정보
     */
    Optional<UserEntity> findFirstByEmailAndStatus(String email, UserStatus status);


}
