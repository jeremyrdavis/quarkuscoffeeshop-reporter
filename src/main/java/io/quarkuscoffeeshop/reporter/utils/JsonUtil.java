package io.quarkuscoffeeshop.reporter.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JsonUtil {

    private static final Logger logger = LoggerFactory.getLogger(JsonUtil.class);

    static ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

    public static String toJsonString(Object object) throws JsonException {

        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error(e.getMessage());
            throw new JsonException(e.getMessage(), e);
        }
    }

}
