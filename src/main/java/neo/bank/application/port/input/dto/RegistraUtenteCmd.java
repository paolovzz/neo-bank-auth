package neo.bank.application.port.input.dto;

import java.time.LocalDate;

import lombok.Value;

@Value
public class RegistraUtenteCmd {

    private String username;
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataNascita;
    private String luogoNascita;
    private String residenza;
    private String password;
    private String telefono;
    private String codiceFiscale;
    
}
