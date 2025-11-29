package exception;

public class ContaNaoEncontradaException extends RuntimeException{

    public ContaNaoEncontradaException(String numero){
        super("Conta não encontrada: "+numero);
    }

}
