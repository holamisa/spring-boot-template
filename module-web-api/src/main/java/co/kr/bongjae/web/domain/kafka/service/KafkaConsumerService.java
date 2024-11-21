package co.kr.bongjae.web.domain.kafka.service;

import co.kr.bongjae.web.domain.kafka.event.MessageEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j // 로깅
@Service // 서비스 로직임을 명시해줌
@RequiredArgsConstructor // final 또는 @NonNull 인자만 가지는 생성자 자동 생성
public class KafkaConsumerService {

    private static final String TOPIC_NAME = "module-web-api-topic";

    @KafkaListener(topics = TOPIC_NAME)
    public void consumeMessage(MessageEvent message) {
        log.info("Consume message: {}", message.toString());
    }
}
