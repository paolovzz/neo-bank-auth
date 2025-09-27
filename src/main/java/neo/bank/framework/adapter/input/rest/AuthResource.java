package neo.bank.framework.adapter.input.rest;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import io.quarkus.security.identity.SecurityIdentity;
import jakarta.annotation.security.RolesAllowed;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.bank.application.UtenteUseCase;
import neo.bank.framework.adapter.input.rest.request.CommandConverter;
import neo.bank.framework.adapter.input.rest.request.LoginUtenteRequest;
import neo.bank.framework.adapter.input.rest.request.RegistraUtenteRequest;
import neo.bank.framework.adapter.input.rest.response.LoginResponse;

@Path("/auth")
@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Slf4j
public class AuthResource {


    private final SecurityIdentity identity;
    private final UtenteUseCase app;

    @POST
    @Path("/registra-utente")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registraUtente(@RequestBody RegistraUtenteRequest request) {

        log.info(("Richiesta registrazione utente"));
        app.registraUtente(CommandConverter.toRegistraUtenteCmd(request));
        log.info(("Richiesta esaurita"));
        return Response.accepted().build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response login(@RequestBody LoginUtenteRequest request) {

        log.info(("Richiesta login"));
        String token = app.login(CommandConverter.toLoginUtenteCmd(request));
        log.info(("Richiesta login completata"));
        return Response.ok(new LoginResponse(token)).build();
    }

    @POST
    @Path("/logout")
    @Consumes(MediaType.APPLICATION_JSON)
    @RolesAllowed("cliente-banca")
    public Response logout(@HeaderParam("Authorization") String authorizationHeader) {


        log.info(("Richiesta logout"));
        // String token = authorizationHeader.substring("Bearer ".length());
        // app.login(CommandConverter.toLoginUtenteCmd(request));
        // log.info(("Richiesta login completata"));
        return Response.ok().build();
    }
}
