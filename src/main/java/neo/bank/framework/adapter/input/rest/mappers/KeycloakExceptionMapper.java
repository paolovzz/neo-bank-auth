package neo.bank.framework.adapter.input.rest.mappers;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import neo.bank.domain.exception.KeycloakException;
import neo.bank.framework.adapter.input.rest.response.ErrorResponse;

@Provider
public class KeycloakExceptionMapper implements ExceptionMapper<KeycloakException> {

    @Override
    public Response toResponse(KeycloakException exception) {
        // Puoi personalizzare il corpo della risposta come preferisci
        ErrorResponse errorResponse = new ErrorResponse("[Keycloak Error]", exception.getMessage());

        return Response.status(exception.getStatus())
                       .entity(errorResponse)
                       .build();
    }
}
