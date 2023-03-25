package pl.Portfolio.bankingapp.Exceptions;

public class TransactionNotFoundException extends RuntimeException{

    public TransactionNotFoundException (String message) {
        super(message);
    }
}
