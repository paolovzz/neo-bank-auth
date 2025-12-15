package neo.bank.framework.adapter.input.rest;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.bank.application.AuthUseCase;
import neo.bank.application.port.input.dto.LoginUtenteCmd;
import neo.bank.application.port.input.dto.LogoutUtenteCmd;
import neo.bank.application.port.input.dto.RegistraUtenteCmd;
import neo.bank.auth.framework.adapter.input.rest.api.AuthApi;
import neo.bank.auth.framework.adapter.input.rest.model.LoginResponse;
import neo.bank.auth.framework.adapter.input.rest.model.LoginUtenteRequest;
import neo.bank.auth.framework.adapter.input.rest.model.RegistraUtenteRequest;
import neo.bank.domain.exception.ValidazioneException;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Slf4j
public class AuthResource implements AuthApi {
    

    private final SecurityIdentity identity;
    private final AuthUseCase app;

    @Override
    public Response loginUtente(LoginUtenteRequest request) {
        log.info(("Richiesta login"));
        String token = app.login(new LoginUtenteCmd(request.getUsername(), request.getPassword()));
        log.info(("Richiesta login completata"));
        return Response.ok(new LoginResponse(token)).build();
    }
    @Override
    public Response logoutUtente(String authorization) {
        log.info("Richiesta logout per [{}]", identity.getPrincipal().getName());
        
        if(authorization == null || authorization.isBlank() || !authorization.startsWith("Bearer ") ) {
            throw new ValidazioneException(AuthResource.class.getSimpleName(), "Header di autorizzazione mancante o non valido");
        }
        String token = authorization.substring("Bearer ".length());
        app.logout(new LogoutUtenteCmd(token));
        log.info(("Richiesta logout completata"));
        return Response.ok().build();
    }
    @Override
    public Response registraUtente(RegistraUtenteRequest request) {
        log.info(("Richiesta registrazione utente"));
        app.registraUtente(new RegistraUtenteCmd(request.getUsername(), request.getNome(), request.getCognome(), request.getEmail(), request.getDataNascita(), request.getResidenza(), request.getPassword(), request.getTelefono(), request.getCodiceFiscale()));
        log.info(("Richiesta esaurita"));
        return Response.accepted().build();
    }
}
