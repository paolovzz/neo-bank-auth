package neo.bank.framework.adapter.output.service;

import java.util.UUID;

import org.apache.kafka.common.header.internals.RecordHeaders;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.eclipse.microprofile.reactive.messaging.Message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.smallrye.reactive.messaging.kafka.api.OutgoingKafkaRecordMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.application.port.output.EmitterOutputPort;
import neo.bank.domain.event.UtenteCancellato;
import neo.bank.domain.event.UtenteRegistrato;

@ApplicationScoped
@Slf4j
public class KafkaEmitterServiceImpl implements EmitterOutputPort{

    private final String OWNER ="NEO_BANK_AUTH";
    @Inject
    private ObjectMapper mapper;

    @Channel("auth-notifications")
    private Emitter<String> emitter;

    @Override
    public void inviaUtenteRegistrato(UtenteRegistrato body) {

        Message<String> message = Message.of(toJsonString(body))
        .addMetadata(OutgoingKafkaRecordMetadata.<String>builder()
                .withHeaders(new RecordHeaders()
                    .add("eventType", "UtenteRegistrato".getBytes())
                    .add("aggregateName", OWNER.getBytes())
                    .add("eventId", UUID.randomUUID().toString().getBytes()))
                .build());
            log.info("Evento inviato: {}", message);
            emitter.send(message);
    }

    @Override
    public void inviaUtenteCancellato(UtenteCancellato body) {

        Message<String> message = Message.of(toJsonString(body))
        .addMetadata(OutgoingKafkaRecordMetadata.<String>builder()
                .withHeaders(new RecordHeaders()
                    .add("eventType", "UtenteRegistrato".getBytes())
                    .add("aggregateName", OWNER.getBytes())
                    .add("eventId", UUID.randomUUID().toString().getBytes()))
                .build());
            log.info("Evento inviato: {}", message);
            emitter.send(message);
    }

    private String toJsonString(Object integrationEvent) {
        try {
            return mapper.writeValueAsString(integrationEvent);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
