package co.kr.bongjae.web.domain.kafka.service;

import co.kr.bongjae.web.domain.kafka.event.MessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j // 로깅
@Service // 서비스 로직임을 명시해줌
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
public class KafkaProducerService {

    @Value("${topic.name}")
    private String topicName;

    private final KafkaTemplate<String, MessageEvent> kafkaTemplate;

    public void sendMessage(MessageEvent messageEvent) {
        log.info("Send message: {}", messageEvent);

        var send = kafkaTemplate.send(topicName, messageEvent);
        send.whenComplete((result, ex) -> {
            if (ex != null) {
                log.error("Failed to send message: {}", ex.getMessage());
            } else {
                log.info("Message sent: {}", result.getProducerRecord().value());
            }
        });
    }
}
