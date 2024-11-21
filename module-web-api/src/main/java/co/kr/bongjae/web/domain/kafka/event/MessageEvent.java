package co.kr.bongjae.web.domain.kafka.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // toString + equals + hashCode + getter + setter + requiredArgsConstructor
@NoArgsConstructor // 인자 없는 생성자
@AllArgsConstructor // 모든 필드를 인자로 받는 생성자
@Builder // 빌더 패턴 등록
public class MessageEvent {

    private String message;
}
