package co.kr.bongjae.db.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@MappedSuperclass // 상속받는 클래스에게 컬럼 매핑 정보만 제공
@Data // toString + equals + hashCode + getter + setter
@SuperBuilder // 상속으로 통한 Builder이기에 필요
@NoArgsConstructor // 인자 없는 생성자
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
public class BaseEntity {

    /**
     * Entity ID
     */
    @Id // PK 값
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성
    private Long id;
}