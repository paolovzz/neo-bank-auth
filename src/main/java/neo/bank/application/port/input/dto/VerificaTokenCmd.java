package neo.bank.application.port.input.dto;

import lombok.Value;

@Value
public class VerificaTokenCmd {

    private String token;
}
