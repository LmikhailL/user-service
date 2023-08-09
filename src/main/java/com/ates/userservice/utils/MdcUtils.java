package com.ates.userservice.utils;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.util.UUID;
import lombok.experimental.UtilityClass;
import org.slf4j.MDC;

@UtilityClass
public class MdcUtils {

  public static final String CORRELATION_ID = "CorrelationId";

  public static String getCorrelationId() {
    String correlationId = MDC.get(CORRELATION_ID);
    return isBlank(correlationId)
        ? UUID.randomUUID().toString()
        : correlationId;
  }

  public static void setCorrelationId(String correlationId) {
    MDC.put(CORRELATION_ID, correlationId);
  }
}
