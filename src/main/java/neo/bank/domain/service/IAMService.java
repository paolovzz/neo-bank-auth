package neo.bank.domain.service;

import neo.bank.domain.model.Token;

public interface IAMService {

    public String refreshToken();
    public String login(String username, String password);
    public void   logout(Token token);
    public void   cancellaUtente();
    void registraUtente(String username, String nome, String cognome, String password, String email);
    
}
