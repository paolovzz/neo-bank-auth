package neo.bank.application.port.out;

import neo.bank.application.port.out.dto.UtenteRegistrato;

public interface EmitterOutputPort {

    public void inviaUtenteRegistrato(UtenteRegistrato body, String eventName);
    
}
