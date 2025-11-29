package model;

import exception.ValorInvalidoException;

import java.time.LocalDate;

public class Conta {

    private String numero;
    private String titular;
    private double saldo;
    private boolean ativa;
    private LocalDate dataInativacao;

    public Conta(String numero, String titular, double saldoInicial) {
        if (saldoInicial < 0){
            throw new ValorInvalidoException("Deposito", String.valueOf(Math.round(saldoInicial*100.0)/100));
        }
        this.numero = numero;
        this.titular = titular;
        this.saldo = saldoInicial;
        this.ativa = true;
        this.dataInativacao = null;
    }

    public String getNumero() {
        return numero;
    }

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public LocalDate getDataInativacao() {
        return dataInativacao;
    }


    public void creditar(double valor) {
        this.saldo += valor;
    }

    public void debitar(double valor) {
        this.saldo -= valor;
    }

    public void inativar() {
        this.ativa = false;
        this.dataInativacao = LocalDate.now();
    }
}