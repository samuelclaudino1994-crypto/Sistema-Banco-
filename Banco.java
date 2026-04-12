import java.util.ArrayList;

public class Banco {

    private ArrayList<Conta> contas = new ArrayList<>();

    public void adicionarConta(Conta conta){
        contas.add(conta); // ✔️ corrigido
    }

    public Conta buscarConta(int numero) {
        for (Conta c : contas) {
            if (c.getNumeroConta() == numero) return c;
        }
        return null;
    }

    public boolean depositar(int numero, double valor) {
        Conta c = buscarConta(numero);
        if (c != null) {
            c.depositar(valor);
            return true;
        }
        return false;
    }

    public boolean sacar(int numero, double valor) {
        Conta c = buscarConta(numero);
        if (c != null) {
            return c.sacar(valor);
        }
        return false;
    }

    public boolean transferir(int origem, int destino, double valor) { // ✔️ corrigido
        Conta c1 = buscarConta(origem);
        Conta c2 = buscarConta(destino);

        if (c1 != null && c2 != null) {
            return c1.transferir(c2, valor);
        }
        return false;
    }
}