package neo.bank.application.port.input.dto;

import lombok.Value;

@Value
public class LogoutUtenteCmd {

    private String token;
}
