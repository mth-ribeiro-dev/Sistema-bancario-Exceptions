
import model.Conta;
import service.BancoService;
import exception.*;

public class Main {

    public static void main(String[] args) {
        BancoService banco = new BancoService();

        System.out.println("=== TESTES DO SISTEMA BANCÁRIO ===\n");

        testarCriacaoContas(banco);
        testarCriacaoContaSaldoNegativo(banco);
        testarDepositoNormal(banco);
        testarDepositoValorNegativo(banco);
        testarDepositoContaInexistente(banco);
        testarSaqueNormal(banco);
        testarSaqueSaldoInsuficiente(banco);
        testarSaqueContaInativa(banco);
        testarTransferenciaNormal(banco);
        testarTransferenciaSaldoInsuficiente(banco);
        testarTransferenciaContaInativa(banco);
        testarTransferenciaContaInexistente(banco);
        testarDepositoContaInativa(banco);

        System.out.println("=== FIM DOS TESTES ===");
    }

    private static void testarCriacaoContas(BancoService banco) {
        System.out.println("TESTE 1: Criar contas normalmente");
        try {
            Conta conta1 = banco.criarConta("001", "João Silva", 1000.00);
            Conta conta2 = banco.criarConta("002", "Maria Santos", 500.00);

            assert conta1.getNumero().equals("001") : "Número incorreto";
            assert conta1.getTitular().equals("João Silva") : "Titular incorreto";
            assert conta1.getSaldo() == 1000.00 : "Saldo incorreto";

            System.out.println("✓ Conta 001 criada para João Silva - Saldo: R$" + conta1.getSaldo());
            System.out.println("✓ Conta 002 criada para Maria Santos - Saldo: R$" + conta2.getSaldo());
            System.out.println("✓ PASSOU\n");
        } catch (Exception e) {
            System.out.println("✗ FALHOU: " + e.getMessage() + "\n");
        }
    }

    private static void testarCriacaoContaSaldoNegativo(BancoService banco) {
        System.out.println("TESTE 2: Criar conta com saldo negativo (deve lançar exceção)");
        try {
            banco.criarConta("003", "Pedro Costa", -100.00);
            System.out.println("✗ FALHOU: Deveria ter lançado ValorInvalidoException\n");
        } catch (ValorInvalidoException e) {
            System.out.println("✓ Exceção capturada corretamente: " + e.getMessage());
            System.out.println("✓ PASSOU\n");
        } catch (Exception e) {
            System.out.println("✗ FALHOU: Exceção inesperada: " + e.getMessage() + "\n");
        }
    }

    private static void testarDepositoNormal(BancoService banco) {
        System.out.println("TESTE 3: Depósito normal");
        try {
            Conta conta = banco.criarConta("004", "Ana Silva", 1000.00);
            double saldoAntes = conta.getSaldo();
            double valor = 200.00;

            banco.depositar(conta.getNumero(), valor);
            double saldoDepois = banco.consultarSaldo(conta.getNumero());
            double saldoEsperado = saldoAntes + valor;

            assert saldoDepois == saldoEsperado : "Saldo não foi atualizado";

            System.out.println("✓ Saldo antes: R$" + saldoAntes);
            System.out.println("✓ Depositado: R$" + valor);
            System.out.println("✓ Saldo depois: R$" + saldoDepois);
            System.out.println("✓ PASSOU\n");
        } catch (Exception e) {
            System.out.println("✗ FALHOU: " + e.getMessage() + "\n");
        }
    }

    private static void testarDepositoValorNegativo(BancoService banco) {
        System.out.println("TESTE 4: Depósito com valor negativo (deve lançar exceção)");
        try {
            Conta conta = banco.criarConta("005", "Carlos Mendes", 1000.00);
            banco.depositar(conta.getNumero(), -50.00);
            System.out.println("✗ FALHOU: Deveria ter lançado ValorInvalidoException\n");
        } catch (ValorInvalidoException e) {
            System.out.println("✓ Exceção capturada corretamente: " + e.getMessage());
            System.out.println("✓ PASSOU\n");
        } catch (Exception e) {
            System.out.println("✗ FALHOU: Exceção inesperada: " + e.getMessage() + "\n");
        }
    }

    private static void testarDepositoContaInexistente(BancoService banco) {
        System.out.println("TESTE 5: Depósito em conta inexistente (deve lançar exceção)");
        try {
            banco.depositar("999", 100.00);
            System.out.println("✗ FALHOU: Deveria ter lançado ContaNaoEncontradaException\n");
        } catch (ContaNaoEncontradaException e) {
            System.out.println("✓ Exceção capturada corretamente: " + e.getMessage());
            System.out.println("✓ PASSOU\n");
        } catch (Exception e) {
            System.out.println("✗ FALHOU: Exceção inesperada: " + e.getMessage() + "\n");
        }
    }

    private static void testarSaqueNormal(BancoService banco) {
        System.out.println("TESTE 6: Saque normal");
        try {
            Conta conta = banco.criarConta("006", "Lucia Oliveira", 1000.00);
            double saldoAntes = banco.consultarSaldo(conta.getNumero());
            double valor = 200.00;

            banco.sacar(conta.getNumero(), valor);
            double saldoDepois = banco.consultarSaldo(conta.getNumero());
            double saldoEsperado = saldoAntes - valor;

            assert saldoDepois == saldoEsperado : "Saldo não foi atualizado";

            System.out.println("✓ Saldo antes: R$" + saldoAntes);
            System.out.println("✓ Sacado: R$" + valor);
            System.out.println("✓ Saldo depois: R$" + saldoDepois);
            System.out.println("✓ PASSOU\n");
        } catch (Exception e) {
            System.out.println("✗ FALHOU: " + e.getMessage() + "\n");
        }
    }

    private static void testarSaqueSaldoInsuficiente(BancoService banco) {
        System.out.println("TESTE 7: Saque com saldo insuficiente (deve lançar exceção)");
        try {
            Conta conta = banco.criarConta("007", "Roberto Alves", 100.00);
            banco.sacar(conta.getNumero(), 500.00);
            System.out.println("✗ FALHOU: Deveria ter lançado SaldoInsuficienteException\n");
        } catch (SaldoInsuficienteException e) {
            System.out.println("✓ Exceção capturada corretamente: " + e.getMessage());
            System.out.println("✓ PASSOU\n");
        } catch (Exception e) {
            System.out.println("✗ FALHOU: Exceção inesperada: " + e.getMessage() + "\n");
        }
    }

    private static void testarSaqueContaInativa(BancoService banco) {
        System.out.println("TESTE 8: Saque em conta inativa (deve lançar exceção)");
        try {
            Conta conta = banco.criarConta("008", "Fernando Costa", 1000.00);
            banco.inativarConta(conta.getNumero());
            banco.sacar(conta.getNumero(), 100.00);
            System.out.println("✗ FALHOU: Deveria ter lançado ContaInativaException\n");
        } catch (ContaInativaException e) {
            System.out.println("✓ Exceção capturada corretamente: " + e.getMessage());
            System.out.println("✓ PASSOU\n");
        } catch (Exception e) {
            System.out.println("✗ FALHOU: Exceção inesperada: " + e.getMessage() + "\n");
        }
    }

    private static void testarTransferenciaNormal(BancoService banco) {
        System.out.println("TESTE 9: Transferência normal");
        try {
            Conta origem = banco.criarConta("009", "Giuliana Santos", 1000.00);
            Conta destino = banco.criarConta("010", "Marcus Lima", 500.00);

            double saldoOrigemAntes = banco.consultarSaldo(origem.getNumero());
            double saldoDestinoAntes = banco.consultarSaldo(destino.getNumero());
            double valor = 300.00;

            banco.transferir(origem.getNumero(), destino.getNumero(), valor);

            double saldoOrigemDepois = banco.consultarSaldo(origem.getNumero());
            double saldoDestinoDepois = banco.consultarSaldo(destino.getNumero());

            assert saldoOrigemDepois == saldoOrigemAntes - valor : "Saldo origem incorreto";
            assert saldoDestinoDepois == saldoDestinoAntes + valor : "Saldo destino incorreto";

            System.out.println("✓ Conta origem antes: R$" + saldoOrigemAntes);
            System.out.println("✓ Conta destino antes: R$" + saldoDestinoAntes);
            System.out.println("✓ Transferido: R$" + valor);
            System.out.println("✓ Conta origem depois: R$" + saldoOrigemDepois);
            System.out.println("✓ Conta destino depois: R$" + saldoDestinoDepois);
            System.out.println("✓ PASSOU\n");
        } catch (Exception e) {
            System.out.println("✗ FALHOU: " + e.getMessage() + "\n");
        }
    }

    private static void testarTransferenciaSaldoInsuficiente(BancoService banco) {
        System.out.println("TESTE 10: Transferência com saldo insuficiente (deve lançar exceção)");
        try {
            Conta origem = banco.criarConta("011", "Sophia Rocha", 100.00);
            Conta destino = banco.criarConta("012", "Vicente Marques", 500.00);
            banco.transferir(origem.getNumero(), destino.getNumero(), 500.00);
            System.out.println("✗ FALHOU: Deveria ter lançado TransferenciaException\n");
        } catch (TransferenciaException e) {
            System.out.println("✓ Exceção capturada corretamente: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("✓ Causa: " + e.getCause().getMessage());
            }
            System.out.println("✓ PASSOU\n");
        } catch (Exception e) {
            System.out.println("✗ FALHOU: Exceção inesperada: " + e.getMessage() + "\n");
        }
    }

    private static void testarTransferenciaContaInativa(BancoService banco) {
        System.out.println("TESTE 11: Transferência de conta inativa (deve lançar exceção)");
        try {
            Conta origem = banco.criarConta("013", "Tatiana Ribeiro", 1000.00);
            Conta destino = banco.criarConta("014", "Ulisses Teixeira", 500.00);
            banco.inativarConta(origem.getNumero());
            banco.transferir(origem.getNumero(), destino.getNumero(), 200.00);
            System.out.println("✗ FALHOU: Deveria ter lançado TransferenciaException\n");
        } catch (TransferenciaException e) {
            System.out.println("✓ Exceção capturada corretamente: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("✓ Causa: " + e.getCause().getMessage());
            }
            System.out.println("✓ PASSOU\n");
        } catch (Exception e) {
            System.out.println("✗ FALHOU: Exceção inesperada: " + e.getMessage() + "\n");
        }
    }

    private static void testarTransferenciaContaInexistente(BancoService banco) {
        System.out.println("TESTE 12: Transferência para conta inexistente (deve lançar exceção)");
        try {
            Conta origem = banco.criarConta("015", "Vanessa Monteiro", 1000.00);
            banco.transferir(origem.getNumero(), "999", 200.00);
            System.out.println("✗ FALHOU: Deveria ter lançado TransferenciaException\n");
        } catch (TransferenciaException e) {
            System.out.println("✓ Exceção capturada corretamente: " + e.getMessage());
            if (e.getCause() != null) {
                System.out.println("✓ Causa: " + e.getCause().getMessage());
            }
            System.out.println("✓ PASSOU\n");
        } catch (Exception e) {
            System.out.println("✗ FALHOU: Exceção inesperada: " + e.getMessage() + "\n");
        }
    }

    private static void testarDepositoContaInativa(BancoService banco) {
        System.out.println("TESTE 13: Depósito em conta inativa (deve funcionar!)");
        try {
            Conta conta = banco.criarConta("016", "Wagner Cardoso", 1000.00);
            banco.inativarConta(conta.getNumero());

            double saldoAntes = banco.consultarSaldo(conta.getNumero());
            double valor = 100.00;

            banco.depositar(conta.getNumero(), valor);
            double saldoDepois = banco.consultarSaldo(conta.getNumero());

            assert saldoDepois == saldoAntes + valor : "Saldo não foi atualizado";

            System.out.println("✓ Conta está inativa");
            System.out.println("✓ Saldo antes: R$" + saldoAntes);
            System.out.println("✓ Depositado: R$" + valor);
            System.out.println("✓ Saldo depois: R$" + saldoDepois);
            System.out.println("✓ Depósito funcionou mesmo com conta inativa!");
            System.out.println("✓ PASSOU\n");
        } catch (Exception e) {
            System.out.println("✗ FALHOU: " + e.getMessage() + "\n");
        }
    }
}