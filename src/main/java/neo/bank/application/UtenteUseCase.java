package neo.bank.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.bank.application.port.input.dto.LoginUtenteCmd;
import neo.bank.application.port.input.dto.LogoutUtenteCmd;
import neo.bank.application.port.input.dto.RegistraUtenteCmd;
import neo.bank.domain.service.IAMService;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Slf4j
public class UtenteUseCase {
    
    private final IAMService iamService;

    public void registraUtente(RegistraUtenteCmd cmd) {
        log.info("Registrazione utente avviata...");
        iamService.registraUtente(cmd.getUsername(), cmd.getNome(), cmd.getCognome(), cmd.getPassword(), cmd.getEmail());
        log.info("Registrazione utente terminata...");
    }

    public String login(LoginUtenteCmd cmd) {
        log.info("Login utente avviato...");
        String token = iamService.login(cmd.getUsername(), cmd.getPassword());
        log.info("Login effettuato...");
        return token;
    }

    public void logout(LogoutUtenteCmd cmd) {
        log.info("Logout utente avviato...");
        iamService.logout(cmd.getToken());
        log.info("Logout effettuato...");
    }
}
