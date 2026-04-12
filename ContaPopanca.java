public class ContaPopanca extends Conta {
    
    private double taxaRendimento; 

    public ContaPopanca(String titular, double saldoInicial, double taxa){
        super(titular, saldoInicial);
        this.taxaRendimento = taxa;        
    }
}