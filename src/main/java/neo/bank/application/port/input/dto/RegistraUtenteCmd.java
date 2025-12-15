package neo.bank.application.port.input.dto;

import java.time.LocalDate;

import lombok.Getter;
import neo.bank.domain.exception.ValidazioneException;


@Getter
public class RegistraUtenteCmd {

    private String username;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataNascita;
    private String residenza;
    private String password;
    private String telefono;
    private String codiceFiscale;



    public RegistraUtenteCmd(String username, String nome, String cognome, String email, LocalDate dataNascita, String residenza, String password, String telefono, String codiceFiscale) {
        
        if(username == null || username.isBlank()) {
            throw new ValidazioneException(RegistraUtenteCmd.class.getSimpleName(), "Username non puo' essere vuoto");
        }
        if(nome == null || nome.isBlank()) {
            throw new ValidazioneException(RegistraUtenteCmd.class.getSimpleName(), "Nome non puo' essere vuoto");
        }
        if(cognome == null || cognome.isBlank()) {
            throw new ValidazioneException(RegistraUtenteCmd.class.getSimpleName(), "Cognome non puo' essere vuoto");
        }
        if(email == null || email.isBlank()) {
            throw new ValidazioneException(RegistraUtenteCmd.class.getSimpleName(), "Email non puo' essere vuoto");
        }
        if(dataNascita == null ) {
            throw new ValidazioneException(RegistraUtenteCmd.class.getSimpleName(), "DataNascita non puo' essere vuota");
        }
        if(residenza == null || residenza.isBlank()) {
            throw new ValidazioneException(RegistraUtenteCmd.class.getSimpleName(), "Residenza non puo' essere vuota");
        }
        if(password == null || password.isBlank()) {
            throw new ValidazioneException(RegistraUtenteCmd.class.getSimpleName(), "Password non puo' essere vuota");
        }
        if(telefono == null || telefono.isBlank()) {
            throw new ValidazioneException(RegistraUtenteCmd.class.getSimpleName(), "Telefono non puo' essere vuoto");
        }
        if(codiceFiscale == null || codiceFiscale.isBlank()) {
            throw new ValidazioneException(RegistraUtenteCmd.class.getSimpleName(), "CodiceFiscale non puo' essere vuoto");
        }       
        
        this.username = username;
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.dataNascita = dataNascita;
        this.residenza = residenza;
        this.password = password;
        this.telefono = telefono;
        this.codiceFiscale = codiceFiscale;
    }           
    
}
