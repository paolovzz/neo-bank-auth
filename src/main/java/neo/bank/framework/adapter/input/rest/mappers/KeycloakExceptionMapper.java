package neo.bank.framework.adapter.input.rest.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import lombok.extern.slf4j.Slf4j;
import neo.bank.auth.framework.adapter.input.rest.model.ErrorResponse;
import neo.bank.domain.exception.KeycloakException;

@Provider
@Slf4j
public class KeycloakExceptionMapper implements ExceptionMapper<KeycloakException> {

    @Override
    public Response toResponse(KeycloakException exception) {
        log.error("Errore Keycloak", exception);
        ErrorResponse errorResponse = new ErrorResponse(exception.getMessage());

        return Response.status(exception.getStatus())
                       .entity(errorResponse)
                       .build();
    }
}
