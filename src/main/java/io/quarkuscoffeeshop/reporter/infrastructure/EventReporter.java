package io.quarkuscoffeeshop.reporter.infrastructure;

import io.quarkuscoffeeshop.reporter.domain.DebeziumEvent;
import io.quarkuscoffeeshop.reporter.utils.JsonException;
import io.quarkuscoffeeshop.reporter.utils.JsonUtil;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class EventReporter {

    private static final Logger logger = LoggerFactory.getLogger(EventReporter.class);

    @RestClient
    RESTService restService;

    public void send(DebeziumEvent debeziumEvent) {

        try {
            String result = restService.send(JsonUtil.toJsonString(debeziumEvent));
            logger.debug("result: {}", result);
        } catch (JsonException e) {
            e.printStackTrace();
            logger.error("error: {}", e.getMessage());
        }
    }

}
