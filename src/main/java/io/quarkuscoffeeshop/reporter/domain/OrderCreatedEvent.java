package io.quarkuscoffeeshop.reporter.domain;

public class OrderCreatedEvent extends DebeziumEvent{

    public OrderCreatedEvent(String orderId, String payload, String timestamp) {
        super("Order", "OrderCreated", orderId, payload, timestamp);
    }
}
