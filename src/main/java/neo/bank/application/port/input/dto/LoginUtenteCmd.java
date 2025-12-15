package neo.bank.application.port.input.dto;

import lombok.Getter;
import neo.bank.domain.exception.ValidazioneException;

@Getter
public class LoginUtenteCmd {

    private String username;
    private String password;


    public LoginUtenteCmd(String username, String password) {
        if(username == null || username.isBlank()) {
            throw new ValidazioneException(LoginUtenteCmd.class.getSimpleName(), "Username non puo' essere vuoto");
        }
        if(password == null || password.isBlank()) {
            throw new ValidazioneException(LoginUtenteCmd.class.getSimpleName(), "Password non puo' essere vuota");
        }
        this.username = username;
        this.password = password;
    }
    
}
