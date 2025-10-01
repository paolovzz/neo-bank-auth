package neo.bank.application.port.input.dto;

import lombok.Value;

@Value
public class AggiornaEmailUtenteCmd {

    private String username;
    private String email;
}
