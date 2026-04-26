import java.util.Scanner;

public class CaixaEletronico {

    public static void mostrarMenu() {
        System.out.println("\n--- MENU ---");
        System.out.println("1 - Depositar");
        System.out.println("2 - Sacar");
        System.out.println("3 - Transferência");
        System.out.println("4 - Extrato");
        System.out.println("5 - Criar conta");
        System.out.println("0 - Sair");
        System.out.print("Opção: ");
    }

    public static void main(String[] args){
        Scanner leitor = new Scanner(System.in);
        Banco banco = new Banco();
        int opcao = -1;

        while (opcao != 0) {
            mostrarMenu();
            opcao = leitor.nextInt();

            try { 
                switch (opcao) {

                    case 1: // depositar
                        System.out.print("Numero da conta: ");
                        int numDep = leitor.nextInt();
                        System.out.print("Valor: ");

                        if (banco.depositar(numDep, leitor.nextDouble()))
                            System.out.println("Sucesso!");
                        else 
                            System.out.println("Conta não encontrada!");
                        break;

                    case 2: // sacar 
                        System.out.print("Numero da conta: ");
                        int numSaq = leitor.nextInt();
                        System.out.print("Valor: ");

                        if (banco.sacar(numSaq, leitor.nextDouble()))
                            System.out.println("Sucesso!");
                        else 
                            System.out.println("Erro no saque!");
                        break;

                    case 3: // transferência
                        System.out.print("Conta origem: ");
                        int origem = leitor.nextInt();

                        System.out.print("Conta destino: ");
                        int destino = leitor.nextInt();

                        System.out.print("Valor: ");
                        double valor = leitor.nextDouble();

                        if (banco.transferir(origem, destino, valor))
                            System.out.println("Transferência realizada com sucesso!");
                        else
                            System.out.println("Erro na transferência!");

                        break;
                    case 4: // extrato 
                        System.out.print("Numero da conta: ");
                        Conta c = banco.buscarConta(leitor.nextInt());

                        if (c != null) c.mostrarExtrato();
                        else System.out.println("Conta não encontrada!");
                        break; // ✔️ importante

                    case 5: // Criar conta 
                        leitor.nextLine(); // limpar buffer

                        System.out.print("Nome: ");
                        String nome = leitor.nextLine();

                        System.out.print("Saldo inicial: ");
                        double saldo = leitor.nextDouble();

                        System.out.print("Tipo (1-Corrente, 2-Poupança): ");
                        int tipo = leitor.nextInt();

                        Conta nova;

                        if (tipo == 1)
                            nova = new ContaCorrente(nome, saldo);
                        else
                            nova = new ContaPopanca(nome, saldo, 0.5);

                        banco.adicionarConta(nova);

                        System.out.println("Conta criada com sucesso!");
                        break;
                }

            } catch (Exception e){
                System.out.println("Erro: " + e.getMessage());
                leitor.nextLine();
            }
        }
    }
}