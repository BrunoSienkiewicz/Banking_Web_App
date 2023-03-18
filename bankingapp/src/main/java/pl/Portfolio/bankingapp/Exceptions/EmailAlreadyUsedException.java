package pl.Portfolio.bankingapp.Exceptions;

public class EmailAlreadyUsedException extends RuntimeException{

    public EmailAlreadyUsedException(String message) {
        super(message);
    }
}
