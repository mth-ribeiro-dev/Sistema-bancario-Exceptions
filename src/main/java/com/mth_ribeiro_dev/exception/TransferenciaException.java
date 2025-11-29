package exception;

public class TransferenciaException extends Exception{

    public TransferenciaException(String contaOrigem, String contaDestino, String valor, Exception causaOriginal){
        super("Falha na transferencia de R$"+valor+" da conta "+contaOrigem+" para "+contaDestino);
    }

}
