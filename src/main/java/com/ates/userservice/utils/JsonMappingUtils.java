package com.ates.userservice.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@UtilityClass
public class JsonMappingUtils {

  public static <T> T mapTo(final String message, final Class<T> messageType) {
    try {
      final ObjectMapper om = JsonMapper.builder().findAndAddModules().build();
      return om.readValue(message, messageType);
    } catch (Exception exception) {
      log.error(exception.getMessage());
    }
    return null;
  }

  public <T> String mapTo(final T message) {
    try {
      final ObjectMapper om = JsonMapper.builder().findAndAddModules().build();
      return om.writeValueAsString(message);
    } catch (Exception exception) {
      log.error(exception.getMessage());
    }
    return null;
  }
}
