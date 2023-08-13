package com.ates.userservice.kafka.consumer;

import static com.ates.userservice.utils.JsonMappingUtils.mapTo;
import static com.ates.userservice.utils.MdcUtils.CORRELATION_ID;
import static com.ates.userservice.utils.MdcUtils.setCorrelationId;

import com.ates.userservice.facade.UserFacade;
import com.ates.userservice.model.UserRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserRegisteredEventConsumer {

  private final UserFacade userFacade;

  @KafkaListener(
      topics = "${spring.kafka.topics.user-registered-topic}",
      groupId = "${spring.kafka.consumer.group-id}"
  )
  public void consume(
      @Payload String message,
      @Header(CORRELATION_ID) String customHeader
  ) {
    setCorrelationId(customHeader);
    log.info("Received UserRegisteredEvent: {}", message);

    UserRegisteredEvent event = mapTo(message, UserRegisteredEvent.class);
    userFacade.on(event);
  }
}
