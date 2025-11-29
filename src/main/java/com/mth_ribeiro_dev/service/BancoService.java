package service;

import model.Conta;
import exception.*;

import java.util.HashMap;
import java.util.Map;

public class BancoService {

    private Map<String, Conta> contas = new HashMap<>();

    /**
     * Cria uma nova conta bancária.
     *
     * @param numero Número da conta
     * @param titular Nome do titular
     * @param saldoInicial Saldo inicial (deve ser >= 0)
     * @return A conta criada
     * @throws ValorInvalidoException se saldoInicial for negativo
     */
    public Conta criarConta(String numero, String titular, double saldoInicial) {
        if (saldoInicial < 0){
            throw new ValorInvalidoException("criar conta", String.valueOf(Math.round(saldoInicial*100.0)/100.0));
        }
        Conta conta = new Conta(numero, titular, saldoInicial);
        contas.put(numero, conta);
        return conta;
    }

    /**
     * Busca uma conta pelo número.
     *
     * @param numero Número da conta
     * @return A conta encontrada
     * @throws ContaNaoEncontradaException se a conta não existir
     */
    public Conta buscarConta(String numero) {
        Conta conta = contas.get(numero);
        if (conta == null){
            throw new ContaNaoEncontradaException(numero);
        }
        return conta;
    }

    /**
     * Realiza depósito em uma conta.
     *
     * @param numeroConta Número da conta
     * @param valor Valor a depositar (deve ser > 0)
     * @throws ContaNaoEncontradaException se a conta não existir
     * @throws ValorInvalidoException se o valor for <= 0
     */
    public void depositar(String numeroConta, double valor) {
        if (valor <= 0){
            throw new ValorInvalidoException("depositar", String.valueOf(Math.round(valor*100.0)/100.0));
        }
        Conta conta = contas.get(numeroConta);
        if (conta == null){
            throw new ContaNaoEncontradaException(numeroConta);
        }
        conta.creditar(valor);
    }

    /**
     * Realiza saque de uma conta.
     *
     * @param numeroConta Número da conta
     * @param valor Valor a sacar (deve ser > 0)
     * @throws ContaNaoEncontradaException se a conta não existir
     * @throws ValorInvalidoException se o valor for <= 0
     * @throws ContaInativaException se a conta estiver inativa
     * @throws SaldoInsuficienteException se não houver saldo suficiente
     */
    public void sacar(String numeroConta, double valor)
            throws ContaInativaException, SaldoInsuficienteException {
        if (valor <= 0){
            throw new ValorInvalidoException("sacar", String.valueOf(Math.round(valor*100.0)/100.0));
        }
        Conta conta = contas.get(numeroConta);
        if (conta == null){
            throw new ContaNaoEncontradaException(numeroConta);
        }
        if (!conta.isAtiva()){
            throw new ContaInativaException(numeroConta, conta.getDataInativacao());
        }
        if (conta.getSaldo() < valor){
            throw new SaldoInsuficienteException(numeroConta,
                    String.valueOf(Math.round(conta.getSaldo()*100.0)/100.0),
                    String.valueOf(Math.round(valor*100.0)/100.0));
        }
        conta.debitar(valor);
    }

    /**
     * Realiza transferência entre contas.
     *
     * @param numeroOrigem Número da conta origem
     * @param numeroDestino Número da conta destino
     * @param valor Valor a transferir (deve ser > 0)
     * @throws TransferenciaException se a transferência falhar por qualquer motivo
     */
    public void transferir(String numeroOrigem, String numeroDestino, double valor)
            throws TransferenciaException {
        try{
            if (valor <= 0){
                throw new ValorInvalidoException("transferir", String.valueOf(Math.round(valor*100.0)/100.0));
            }
            Conta contaOrigem = contas.get(numeroOrigem);
            Conta contaDestino = contas.get(numeroDestino);
            if (contaOrigem == null){
                throw new ContaNaoEncontradaException(numeroOrigem);
            }else if(contaDestino == null){
                throw new ContaNaoEncontradaException(numeroDestino);
            }
            if (!contaOrigem.isAtiva()){
                throw new ContaInativaException(numeroOrigem, contaOrigem.getDataInativacao());
            }
            if (contaOrigem.getSaldo() < valor){
                throw new SaldoInsuficienteException(numeroOrigem, String.valueOf(Math.round(contaOrigem.getSaldo()*100.0)/100.0), String.valueOf(Math.round(valor*100.0)/100.0));
            }
            contaOrigem.debitar(valor);
            contaDestino.creditar(valor);
        } catch (Exception e) {
            throw new TransferenciaException(numeroOrigem, numeroDestino, String.valueOf(Math.round(valor*100.0)/100.0), e);
        }
    }

    /**
     * Inativa uma conta.
     *
     * @param numeroConta Número da conta
     * @throws ContaNaoEncontradaException se a conta não existir
     */
    public void inativarConta(String numeroConta) {
        Conta conta = contas.get(numeroConta);
        if (conta == null){
            throw new ContaNaoEncontradaException(numeroConta);
        }
        conta.inativar();
    }

    /**
     * Retorna o saldo de uma conta.
     *
     * @param numeroConta Número da conta
     * @return O saldo atual
     * @throws ContaNaoEncontradaException se a conta não existir
     */
    public double consultarSaldo(String numeroConta) {
        Conta conta = contas.get(numeroConta);
        if (conta == null){
            throw new ContaNaoEncontradaException(numeroConta);
        }
        return conta.getSaldo();
    }
}