package neo.bank.framework.adapter.output.rest;


import java.util.List;
import java.util.Map;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import neo.bank.domain.model.Ruolo;
import neo.bank.domain.model.Token;

@Path("/")
@RegisterRestClient(configKey = "keycloak-client")
public interface KeycloakClient {

    @POST
    @Path("/realms/{realm}/protocol/openid-connect/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    Token loginAdmin(
        @PathParam("realm") String realm,
        @FormParam("grant_type") String grantType,        // "password"
        @FormParam("client_id") String clientId,
        @FormParam("client_secret") String clientSecret,
        @FormParam("username") String username,
        @FormParam("password") String password
    );

    // === CLIENT CREDENTIALS TOKEN ===
    @POST
    @Path("/realms/{realm}/protocol/openid-connect/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    Token getTokenWithClientCredentials(
        @PathParam("realm") String realm,
        @FormParam("grant_type") String grantType,
        @FormParam("client_id") String clientId,
        @FormParam("client_secret") String clientSecret
    );

    // === PASSWORD GRANT ===
    @POST
    @Path("/realms/{realm}/protocol/openid-connect/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    Token login(
        @PathParam("realm") String realm,
        @FormParam("grant_type") String grantType,
        @FormParam("client_id") String clientId,
        @FormParam("client_secret") String clientSecret,
        @FormParam("username") String username,
        @FormParam("password") String password
    );

    // === REFRESH TOKEN ===
    @POST
    @Path("/realms/{realm}/protocol/openid-connect/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    Token refreshToken(
        @PathParam("realm") String realm,
        @FormParam("grant_type") String grantType,
        @FormParam("client_id") String clientId,
        @FormParam("client_secret") String clientSecret,
        @FormParam("username") String username,
        @FormParam("password") String password
    );

    // === LOGOUT (opzionale) ===
    @POST
    @Path("/realms/{realm}/protocol/openid-connect/logout")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    Response logout(
        @PathParam("realm") String realm,
        @FormParam("client_id") String clientId,
        @FormParam("client_secret") String clientSecret,
        @FormParam("refresh_token") String refreshToken
    );

    // === CREA UTENTE ===
    @POST
    @Path("/admin/realms/{realm}/users")
    @Consumes(MediaType.APPLICATION_JSON)
    Response createUser(
        @HeaderParam("Authorization") String bearerToken,
        @PathParam("realm") String realm,
        Map<String, Object> user
    );


    @GET
    @Path("/admin/realms/{realm}/roles/{roleName}")
    @Produces(MediaType.APPLICATION_JSON)
    Ruolo getRealmRole(
        @HeaderParam("Authorization") String token,
        @PathParam("realm") String realm,
        @PathParam("roleName") String roleName);
    
    @GET
    @Path("/admin/realms/{realm}/users")
    List<Map<String, Object>> getUsersByUsername(@HeaderParam("Authorization") String authHeader,
                                                 @PathParam("realm") String realm,
                                                 @QueryParam("username") String username);



    @POST
    @Path("/admin/realms/{realm}/users/{userId}/role-mappings/realm")
    @Consumes(MediaType.APPLICATION_JSON)
    void assignRealmRoleToUser(@HeaderParam("Authorization") String token,
                                @PathParam("realm") String realm,
                                @PathParam("userId") String userId,
                                List<Ruolo> roles);


    @DELETE
    @Path("/admin/realms/{realm}/users/{userId}")
    void deleteUser(@HeaderParam("Authorization") String token,
                    @PathParam("realm") String realm,
                    @PathParam("userId") String userId);

}
