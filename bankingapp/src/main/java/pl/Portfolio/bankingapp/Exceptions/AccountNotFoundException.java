package pl.Portfolio.bankingapp.Exceptions;

public class AccountNotFoundException extends RuntimeException{

    public AccountNotFoundException (String message) {
        super(message);
    }
}
