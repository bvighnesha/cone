package io.cone;

import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class BannedUserService {

    @RestClient
    BannedUserClient bannedUserClient;

    public String isBanned(String username) {
        String banned = bannedUserClient.isBanned(username);
        return banned;
    }
}
