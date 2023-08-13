package com.ates.userservice.kafka.producer;

import static com.ates.userservice.utils.MdcUtils.CORRELATION_ID;
import static com.ates.userservice.utils.MdcUtils.getCorrelationId;
import static java.util.Objects.nonNull;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.support.SendResult;

/**
 * This class is used to construct producers
 *
 * @param <M> type of the DTO we send to the Kafka
 */
@Slf4j
public abstract class ProducerTemplate<M> {

  public void log(
      final M message,
      final SendResult<String, M> result,
      final Throwable ex,
      final String corId) {
    if (nonNull(ex)) {
      log.error(corId
              + "Can't send message: {} to the topic: {} with key: {}, "
              + "with headers: {}",
          message,
          result.getProducerRecord().topic(),
          result.getProducerRecord().key(),
          result.getProducerRecord().headers(),
          ex
      );
    } else {
      log.info(corId
              + "Message produced successfully! Sent to the topic: {}, "
              + "to the partition: {}",
          result.getRecordMetadata().topic(),
          result.getRecordMetadata().partition()
      );
    }
  }

  public ProducerRecord<String, M> getProducerRecord(
      final M message,
      final String topic
  ) {
    final Header header = new RecordHeader(CORRELATION_ID, getCorrelationId().getBytes());

    return new ProducerRecord<String, M>(
        topic,
        null,
        null,
        message,
        List.of(header)
    );
  }
}
