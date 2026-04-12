public abstract class Conta {
    private static int contador = 1; 
    protected int numeroConta;
    protected String titular;
    protected double saldo;

    public Conta(String titurlar, double saldoInicial) {
        this.numeroConta = contador++;
        this.titular = titurlar;
        this.saldo = saldoInicial;
    }
    
    public int getNumeroConta() {
        return numeroConta;
    }

    public void depositar(double valor) {
        saldo += valor;
    }
    
 public boolean sacar(double valor) {
    if (saldo >= valor) {
        saldo -= valor; 
        return true;
    }
    return false;
}
    public boolean transferir(Conta destino, double valor) {
        if (this.sacar(valor)) {
            destino.depositar(valor);
            return true;
        }
        return false;
    }
    public void mostrarExtrato() {
        System.out.println("conta: " + numeroConta);
        System.out.println("Titular: " + titular);
        System.out.println("Saldo: " + saldo);
    }

}