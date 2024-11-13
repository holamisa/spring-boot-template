package co.kr.bongjae.db.mapper;

import co.kr.bongjae.db.model.user.UserEntity;

import java.util.List;

/**
 * 회원 매퍼
 * Mybatis
 */
public interface UserMapper {

    /**
     * 회원 전체 조회
     * @return 회원 리스트
     */
    List<UserEntity> selectAllUser();
}
