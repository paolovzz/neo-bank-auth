package neo.bank.framework.adapter.input.rest.request;

import neo.bank.application.port.input.dto.LoginUtenteCmd;
import neo.bank.application.port.input.dto.LogoutUtenteCmd;
import neo.bank.application.port.input.dto.RegistraUtenteCmd;
import neo.bank.application.port.input.dto.VerificaTokenCmd;

public class CommandConverter {

    public static final RegistraUtenteCmd toRegistraUtenteCmd(RegistraUtenteRequest req) {
        return new RegistraUtenteCmd(
            req.getUsername(), 
            req.getNome(), 
            req.getCognome(), 
            req.getEmail(), 
            req.getDataNascita(),
            req.getResidenza(), 
            req.getPassword(),
            req.getTelefono(),
            req.getCodiceFiscale());
    }

    public static final LoginUtenteCmd toLoginUtenteCmd(LoginUtenteRequest req) {
        return new LoginUtenteCmd(
            req.getUsername(), 
            req.getPassword());
    }

    public static final LogoutUtenteCmd toLogoutUtenteCmd(String token) {
        return new LogoutUtenteCmd(token);
    }

    public static final VerificaTokenCmd toVerificaTokenCmd(String token) {
        return new VerificaTokenCmd(token);
    }

}
