package neo.bank.application.port.input.dto;

import lombok.Value;

@Value
public class LoginUtenteCmd {

    private String username;
    private String password;
    
}
