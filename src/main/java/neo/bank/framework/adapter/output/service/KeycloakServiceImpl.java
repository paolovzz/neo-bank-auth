package neo.bank.framework.adapter.output.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.microprofile.rest.client.inject.RestClient;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.WebApplicationException;
import lombok.extern.slf4j.Slf4j;
import neo.bank.domain.exception.KeycloakException;
import neo.bank.domain.model.Ruolo;
import neo.bank.domain.model.Token;
import neo.bank.domain.service.IAMService;
import neo.bank.framework.adapter.output.rest.KeycloakClient;


@ApplicationScoped
@Slf4j
public class KeycloakServiceImpl implements IAMService{

    private static final String REALM = "quarkus-app";
    private static final String CLIENT_ID = "banca-app";
    private static final String CLIENT_SECRET = "banca-secret";
    private static final String RUOLO_CLENTE = "cliente";

    @RestClient
    private final KeycloakClient client;

    @Inject
    public KeycloakServiceImpl(@RestClient KeycloakClient client) {
        this.client = client;
    }

    @Override
    public void registraUtente(String username, String nome, String cognome, String password, String email) {

        Token token = client.loginAdmin(REALM, "password", CLIENT_ID, CLIENT_SECRET, "admin", "admin");
        final String tokenHeader = "Bearer " + token.getAccess_token();
        Map<String, Object> user = new HashMap<>();
        user.put("username", username);
        user.put("email", email);
        user.put("firstName", nome);
        user.put("lastName", cognome);
        user.put("enabled", true);
        user.put("emailVerified", true);

        Map<String, Object> credential = new HashMap<>();
        credential.put("type", "password");
        credential.put("value", password);
        credential.put("temporary", false);
        user.put("credentials", Collections.singletonList(credential));

        try {
            client.createUser(tokenHeader, REALM, user);
            // 2. Recupera lo userId dell’utente appena creato
            String userId = null;
            var users = client.getUsersByUsername(tokenHeader, REALM, username);
            userId = (String) users.get(0).get("id");
            Ruolo ruolo = client.getRealmRole(tokenHeader, REALM, RUOLO_CLENTE);
            client.assignRealmRoleToUser(tokenHeader, REALM, userId, List.of(ruolo));

        } catch (WebApplicationException ex) {
            log.error("Errore", ex);
                throw new KeycloakException(ex, ex.getResponse().getStatus(), ex.getMessage());
        }
    }

    @Override
    public String refreshToken() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'refreshToken'");
    }

    @Override
    public String login(String username, String password) {
        String token = client.login(REALM, "password", CLIENT_ID, CLIENT_SECRET, username, password).getAccess_token();
        log.info("TOKEN: {}", token);
        return token;
    }

    @Override
    public void logout(String token) {

        Token tokenObj = client.refreshToken(REALM, "password", CLIENT_ID, CLIENT_SECRET, "admin", "admin");
        client.logout(REALM, CLIENT_ID, CLIENT_SECRET, tokenObj.getRefresh_token());
    }

    @Override
    public void cancellaUtente(String username) {

        Token token = client.loginAdmin(REALM, "password", CLIENT_ID, CLIENT_SECRET, "admin", "admin");
        final String tokenHeader = "Bearer " + token.getAccess_token();
        var users = client.getUsersByUsername(tokenHeader, REALM, username);
        String userId = (String) users.get(0).get("id");
        client.deleteUser(tokenHeader, REALM, userId);
    }
    
}
