package neo.bank.framework.adapter.output.service;

import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import neo.bank.application.port.out.EmitterOutputPort;
import neo.bank.application.port.out.dto.UtenteRegistrato;

@ApplicationScoped
@Slf4j
public class KafkaEmitterServiceImpl implements EmitterOutputPort{

    @Override
    public void inviaUtenteRegistrato(UtenteRegistrato body, String eventName) {
        log.info("TODO invia utente registrato...");
    }
    
}
