package exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ContaInativaException extends Exception{

    public ContaInativaException(String conta, LocalDate data){
        super("Conta "+conta+" está inativa desde "+data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

}
