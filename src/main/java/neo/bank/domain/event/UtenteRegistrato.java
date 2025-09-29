package neo.bank.domain.event;

import java.time.LocalDate;

import lombok.Value;

@Value
public class UtenteRegistrato {
    
    private String nome;
    private String cognome;
    private String email;
    private LocalDate dataNascita;
    private String luogoNascita;
    private String residenza;
    private String telefono;
    private String codiceFiscale;
    private String username;
}
