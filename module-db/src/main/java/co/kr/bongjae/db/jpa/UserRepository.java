package co.kr.bongjae.db.jpa;

import co.kr.bongjae.db.model.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 회원 레파지토리
 * JPA
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
