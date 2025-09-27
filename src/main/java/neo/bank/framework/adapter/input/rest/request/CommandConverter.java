package neo.bank.framework.adapter.input.rest.request;

import neo.bank.application.port.input.dto.LoginUtenteCmd;
import neo.bank.application.port.input.dto.RegistraUtenteCmd;

public class CommandConverter {

    public static final RegistraUtenteCmd toRegistraUtenteCmd(RegistraUtenteRequest req) {
        return new RegistraUtenteCmd(
            req.getUsername(), 
            req.getNome(), 
            req.getCognome(), 
            req.getEmail(), 
            req.getDataNascita(),
            req.getLuogoNascita(), 
            req.getResidenza(), 
            req.getPassword());
    }

    public static final LoginUtenteCmd toLoginUtenteCmd(LoginUtenteRequest req) {
        return new LoginUtenteCmd(
            req.getUsername(), 
            req.getPassword());
    }

}
