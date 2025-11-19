package neo.bank.application;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import neo.bank.application.port.input.dto.AggiornaEmailUtenteCmd;
import neo.bank.application.port.input.dto.CancellaUtenteCmd;
import neo.bank.application.port.input.dto.LoginUtenteCmd;
import neo.bank.application.port.input.dto.LogoutUtenteCmd;
import neo.bank.application.port.input.dto.RegistraUtenteCmd;
import neo.bank.application.port.input.dto.VerificaTokenCmd;
import neo.bank.application.port.output.EmitterOutputPort;
import neo.bank.application.port.output.IAMOutputPort;
import neo.bank.domain.event.UtenteCancellato;
import neo.bank.domain.event.UtenteRegistrato;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor = @__(@Inject))
@Slf4j
public class AuthUseCase {
    
    private final IAMOutputPort iamService;
    private final EmitterOutputPort emitterOutputPort;

    public void registraUtente(RegistraUtenteCmd cmd) {
        log.info("Registrazione utente avviata...");
        iamService.registraUtente(cmd.getUsername(), cmd.getNome(), cmd.getCognome(), cmd.getPassword(), cmd.getEmail());
        UtenteRegistrato utenteRegistrato = new UtenteRegistrato(cmd.getNome(), cmd.getCognome(), cmd.getEmail(), cmd.getDataNascita(), cmd.getResidenza(), cmd.getTelefono(), cmd.getCodiceFiscale(), cmd.getUsername());
        emitterOutputPort.inviaUtenteRegistrato(utenteRegistrato);
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

    public boolean verificaToken(VerificaTokenCmd cmd) {
        log.info("Verifica del token avviata...");
        boolean result = iamService.verificaToken(cmd.getToken());
        log.info("Verifica del token effettuata...");
        return result;
    }

    public void cancellaUtente(CancellaUtenteCmd cmd) {
        log.info("Cancellazione utente avviata...");
        iamService.cancellaUtente(cmd.getUsername());
        UtenteCancellato event = new UtenteCancellato(cmd.getUsername());
        emitterOutputPort.inviaUtenteCancellato(event);
        log.info("Cancellazione utente terminata...");
    }

    public void aggiornaEmailCliente(AggiornaEmailUtenteCmd cmd) {
        log.info("Aggiornamento email utente avviato...");
        iamService.aggiornaEmailCliente(cmd.getUsername(), cmd.getEmail());
        log.info("Aggiornamento email utente terminato...");
    }
}
