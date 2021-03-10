package io.quarkuscoffeeshop.reporter.infrastructure;

import com.github.tomakehurst.wiremock.WireMockServer;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkuscoffeeshop.reporter.domain.OrderCreatedEvent;
import io.quarkuscoffeeshop.reporter.utils.JsonUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class HomeofficeSendTest {

    private static final Logger logger = LoggerFactory.getLogger(HomeofficeSendTest.class);

    static final int PORT = 8090;

    static final WireMockServer WIRE_MOCK_SERVER = new WireMockServer(PORT);

    @Inject
    EventReporter eventReporter;

    @BeforeAll
    public static void setUp() {
        WIRE_MOCK_SERVER.start();
    }

    @AfterAll
    public static void tearDown() {
        WIRE_MOCK_SERVER.stop();
    }

    @Test
    public void testSendingOrderCreatedEvent() {

        try {

            OrderCreatedEvent orderCreatedEvent = mockOrderCreatedEvent();
            configureFor("localhost", PORT);
            givenThat(post(urlEqualTo("/homeoffice/events"))
                    .withRequestBody(equalTo(JsonUtil.toJsonString(orderCreatedEvent)))
                    .willReturn(aResponse().withStatus(200)));

            eventReporter.send(orderCreatedEvent);

            verify(postRequestedFor(urlEqualTo("/homeoffice/events"))
//                    .withHeader("Content-Type", equalTo("application/json"))
                    .withRequestBody(equalTo(JsonUtil.toJsonString(orderCreatedEvent))));

        } catch (Exception e) {
            assertNull(e);
        }
    }

    OrderCreatedEvent mockOrderCreatedEvent() {

        String aggregateId = "2e9689c0-5a82-4563-8bfd-89043e808f46";
        String aggregateType = "Order";
        String type = "OrderCreated";
        String timestamp = "2021-03-09 14:28:46.727491";
        String payload = "{\"orderId\":\"2e9689c0-5a82-4563-8bfd-89043e808f46\",\"orderSource\":\"WEB\",\"timestamp\":\"2021-03-09T19:28:46.727491Z\",\"baristaLineItems\":[{\"item\":\"COFFEE_BLACK\",\"name\":\"Jeremy\"}]}";

        return new OrderCreatedEvent(aggregateId, payload, timestamp);
    }
}
