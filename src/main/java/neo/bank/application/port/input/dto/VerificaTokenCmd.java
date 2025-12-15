package neo.bank.application.port.input.dto;

import lombok.Getter;
import neo.bank.domain.exception.ValidazioneException;

@Getter
public class VerificaTokenCmd {

    private String token;

    public VerificaTokenCmd(String token) {

        if(token == null || token.isBlank()) {
            throw new ValidazioneException(VerificaTokenCmd.class.getSimpleName(), "Token non puo' essere vuoto");
        }       
        this.token = token;
    }   
}
