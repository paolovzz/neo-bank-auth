package neo.bank.domain.service;

import neo.bank.domain.event.UtenteCancellato;
import neo.bank.domain.event.UtenteRegistrato;

public interface EmitterOutputPort {

    public void inviaUtenteRegistrato(UtenteRegistrato body);

    void inviaUtenteCancellato(UtenteCancellato body);
    
}
