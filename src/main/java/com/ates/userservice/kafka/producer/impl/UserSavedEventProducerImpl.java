package com.ates.userservice.kafka.producer.impl;

import static com.ates.userservice.utils.MdcUtils.getCorrelationId;

import com.ates.userservice.kafka.producer.ProducerTemplate;
import com.ates.userservice.kafka.producer.UserSavedEventProducer;
import com.avro.events.streaming.UserSavedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserSavedEventProducerImpl
    extends ProducerTemplate<UserSavedEvent>
    implements UserSavedEventProducer {

  private final KafkaTemplate<String, UserSavedEvent> template;

  @Value("${spring.kafka.topics.user-saved-topic}")
  private String topic;

  @Override
  public void produce(UserSavedEvent event) {
    log.info("Started processing UserSavedEvent: {}", event);

    template.send(getProducerRecord(event, topic))
        .whenComplete((msg, ex) -> log(event, msg, ex, getCorrelationId()));
  }
}
