import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Conta {
    private static int contador = 1;

    protected int numeroConta;
    protected String titular;
    protected double saldo;

    // 🔒 controle de saque diário
    protected double saqueDiario = 0;
    protected LocalDate dataUltimoSaque = LocalDate.now();

    // 🧾 extrato
    protected ArrayList<Transacao> extrato = new ArrayList<>();

    public Conta(String titular, double saldoInicial) {
        this.numeroConta = contador++;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public void depositar(double valor) {
        saldo += valor;
        extrato.add(new Transacao("Depósito", valor));
    }

    public boolean sacar(double valor) {
        LocalDate hoje = LocalDate.now();

        // reset diário
        if (!hoje.equals(dataUltimoSaque)) {
            saqueDiario = 0;
            dataUltimoSaque = hoje;
        }

        if (valor > saldo) {
            System.out.println("Saldo insuficiente!");
            return false;
        }

        if ((saqueDiario + valor) > 1000) {
            System.out.println("Limite diário de saque atingido!");
            return false;
        }

        saldo -= valor;
        saqueDiario += valor;

        extrato.add(new Transacao("Saque", valor));
        return true;
    }

    public boolean transferir(Conta destino, double valor) {
        if (this.sacar(valor)) {
            destino.depositar(valor);

            extrato.add(new Transacao("Transferência enviada", valor));
            destino.extrato.add(new Transacao("Transferência recebida", valor));

            return true;
        }
        return false;
    }

    public void mostrarExtrato() {
    
    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    
    System.out.println("\n=== EXTRATO ===");
    System.out.println("Conta: " + numeroConta);
    System.out.println("Titular: " + titular);
    System.out.println("--------------------------------------------------");

    
    System.out.printf("%-15s | %-12s | %s\n", "TIPO", "VALOR", "DATA");
    System.out.println("--------------------------------------------------");

    for (Transacao t : extrato) {
        String dataFormatada = t.dataHora.format(formato);
        
        
        
        System.out.printf("%-15s R$ %10.2f   %s\n", 
                t.tipo, t.valor, dataFormatada);
    }

    System.out.println("--------------------------------------------------");
    
     
    
    System.out.printf("SALDO ATUAL:    R$ %10.2f\n", saldo);
    System.out.println("==================================================");
    }
}