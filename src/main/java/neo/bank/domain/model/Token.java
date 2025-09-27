package neo.bank.domain.model;

import lombok.Data;

@Data
public class Token {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private Long expires_in;
    private Long refresh_expires_in;
}
