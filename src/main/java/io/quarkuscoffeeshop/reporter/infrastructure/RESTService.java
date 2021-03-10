package io.quarkuscoffeeshop.reporter.infrastructure;

import io.quarkuscoffeeshop.reporter.domain.DebeziumEvent;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

@RegisterRestClient @Path("/homeoffice/events")
public interface RESTService {

    @POST
    public String send(String json);
}
