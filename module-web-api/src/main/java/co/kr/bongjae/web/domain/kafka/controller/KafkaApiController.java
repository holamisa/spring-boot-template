package co.kr.bongjae.web.domain.kafka.controller;

import co.kr.bongjae.web.common.api.ApiResult;
import co.kr.bongjae.web.domain.kafka.event.MessageEvent;
import co.kr.bongjae.web.domain.kafka.service.KafkaProducerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController // Rest 컨트롤러임을 명시해줌
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
@RequestMapping("/open-api/kafka") // API 주소 매핑
@Tag(name = "Kafka Open Api", description = "Kafka Open API Document") // Swagger 문서화
public class KafkaApiController {

    /**
     * Kafka 서비스
     */
    private final KafkaProducerService kafkaProducerService;

    @PostMapping("/send-message")
    public ApiResult<Object> sendMessage(
            @RequestBody MessageEvent messageEvent) {

        kafkaProducerService.sendMessage(messageEvent);
        return ApiResult.OK();
    }
}
