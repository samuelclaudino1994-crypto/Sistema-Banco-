import java.time.LocalDateTime;

public class Transacao {

    String tipo;
    double valor;
    LocalDateTime dataHora;

    public Transacao(String tipo, double valor) {
        this.tipo = tipo;
        this.valor = valor;
        this.dataHora = LocalDateTime.now();
    }
}