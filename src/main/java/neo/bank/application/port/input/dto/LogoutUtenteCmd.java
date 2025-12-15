package neo.bank.application.port.input.dto;

import lombok.Getter;
import neo.bank.domain.exception.ValidazioneException;

@Getter
public class LogoutUtenteCmd {

    private String token;

    public LogoutUtenteCmd(String token) {

        if(token == null || token.isBlank()) {
            throw new ValidazioneException(LogoutUtenteCmd.class.getSimpleName(), "Token non puo' essere vuoto");
        }
        this.token = token;
    }   
}
