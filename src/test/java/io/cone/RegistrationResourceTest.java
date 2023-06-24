package io.cone;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestHTTPEndpoint(RegistrationResource.class)
public class RegistrationResourceTest {
    @InjectMock
    @RestClient
    BannedUserClient bannedUserClient;

    @Test
    public void shouldNotAddAUserIfBannedName() {
        Mockito.when(bannedUserClient.isBanned("Alex")).thenReturn("false");

        User user = new User("Alex", "asotobu@example.com");

        given()
                .body(user)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
                .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
                .when().post()
                .then()
                .statusCode(Response.Status.PRECONDITION_FAILED.getStatusCode());
    }
}