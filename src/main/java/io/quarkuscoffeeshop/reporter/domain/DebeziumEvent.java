package io.quarkuscoffeeshop.reporter.domain;

import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class DebeziumEvent {

    protected static ObjectMapper mapper = new ObjectMapper();

    protected String aggregateType;
    protected String type;

    protected final String aggregateId;
    protected final String payload;
    protected final String timestamp;

    protected DebeziumEvent(String type, String eventType, String aggregateId, String payload, String timestamp) {
        this.aggregateType = type;
        this.type = eventType;
        this.aggregateId = aggregateId;
        this.payload = payload;
        this.timestamp = timestamp;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public String getAggregateType() {
        return aggregateType;
    }

    public String getPayload() {
        return payload;
    }

    public String getType() {
        return type;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
