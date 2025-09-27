package neo.bank.domain.exception;

import lombok.Getter;

@Getter
public class KeyCloakException extends RuntimeException {

    private int status;
    private String message;
    public KeyCloakException(Throwable ex, int status, String message) {
        super(ex);
        this.status = status;
        this.message = message;
    }
    
}
