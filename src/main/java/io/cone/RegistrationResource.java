package io.cone;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/registration")
public class RegistrationResource {

    @Inject
    BannedUserService bannedUserService;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response insertUser(User user) {

        if (Boolean.valueOf(bannedUserService.isBanned(user.username()))) {
            return Response.status(Response.Status.PRECONDITION_FAILED.getStatusCode())
                    .build();
        } else {
           return Response.ok("true").build();
        }
    }
}