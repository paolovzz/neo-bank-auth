package neo.bank.application.port.input.dto;

import lombok.Value;
import neo.bank.domain.model.Token;

@Value
public class LogoutUtenteCmd {

    private Token token;
}
