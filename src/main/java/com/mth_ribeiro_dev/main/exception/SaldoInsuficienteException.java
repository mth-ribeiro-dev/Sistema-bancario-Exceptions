package exception;


public class SaldoInsuficienteException extends Exception{

    public SaldoInsuficienteException(String conta, String saldo, String valorSolicitado){
        super("Saldo insuficiente na conta "+conta+". Saldo: R$"+saldo+". Solicitado: "+valorSolicitado);
    }

}
