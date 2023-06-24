package io.cone;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@Path("/api")
@RegisterRestClient
@ApplicationScoped
public interface BannedUserClient {
    @GET
    @Path("/{username}")
    @Produces(MediaType.TEXT_PLAIN)
    String isBanned(@PathParam("username") String username);
}
