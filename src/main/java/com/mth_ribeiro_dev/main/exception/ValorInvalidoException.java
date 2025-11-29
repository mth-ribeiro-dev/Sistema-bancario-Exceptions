package exception;

public class ValorInvalidoException extends RuntimeException{

    public ValorInvalidoException(String operacao, String valor){
        super("Valor invalido para "+operacao+": R$"+valor+". O valor deve ser positivo");
    }
}
