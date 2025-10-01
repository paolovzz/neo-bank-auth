package neo.bank.framework.adapter.input.rest.input.kafka;

import java.util.concurrent.CompletionStage;

import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.smallrye.reactive.messaging.annotations.Blocking;
import io.smallrye.reactive.messaging.kafka.api.IncomingKafkaRecordMetadata;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import neo.bank.application.UtenteUseCase;
import neo.bank.application.port.input.dto.AggiornaEmailUtenteCmd;
import neo.bank.application.port.input.dto.CancellaUtenteCmd;

@ApplicationScoped
@Slf4j
public class ClienteConsumer {

    @Inject
    private ObjectMapper mapper;

    private static final String EVENT_OWNER = "CLIENTE";
    private static final String EVENT_CREAZIONE_CLIENTE_FALLITA = "CreazioneClienteFallita";
    private static final String EVENT_EMAIL_AGGIORNATA = "EmailAggiornata";

    @Inject
    private UtenteUseCase app;

    @Incoming("cliente-notifications")
    @Blocking
    public CompletionStage<Void> consume(Message<String> msg) {
            var metadata = msg.getMetadata(IncomingKafkaRecordMetadata.class).orElseThrow();
            String eventType = new String(metadata.getHeaders().lastHeader("eventType").value());
            String aggregateName = new String(metadata.getHeaders().lastHeader("aggregateName").value());
            String payload = msg.getPayload();
            log.info("INCOMING:\n- EventType => {}\n- AggregateName => {}", eventType, aggregateName);
            if (aggregateName.equals(EVENT_OWNER)) {
                JsonNode json = convertToJsonNode(payload);
                switch (eventType) {
                    case EVENT_CREAZIONE_CLIENTE_FALLITA:{
                        String usernameCliente = json.get("usernameCliente").asText();
                        app.cancellaUtente(new CancellaUtenteCmd(usernameCliente));
                        break;
                    }
                    case EVENT_EMAIL_AGGIORNATA:{
                        String usernameCliente = json.get("username").asText();
                        String emailCliente = json.get("email").asText();
                        app.aggiornaEmailCliente(new AggiornaEmailUtenteCmd(usernameCliente, emailCliente));
                        break;
                    }
                    default:
                        log.warn("Evento [{}] non gestito...", eventType);
                        break;
                }
            } else {
                log.warn("Owner non gestito [{}]", aggregateName);
            }
        return msg.ack();
    }

    private JsonNode convertToJsonNode(String payload) {
        try {
            return mapper.readTree(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Errore durante la conversione json del messaggio kafka", e);
        }
    }
}
