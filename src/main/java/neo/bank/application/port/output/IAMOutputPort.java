package neo.bank.application.port.output;

public interface IAMOutputPort {

    public String login(String username, String password);
    public void   logout(String token);
    public void   cancellaUtente(String username);
    void registraUtente(String username, String nome, String cognome, String password, String email);
    public void aggiornaEmailCliente(String username, String email);
    public boolean verificaToken(String token);
    
}
