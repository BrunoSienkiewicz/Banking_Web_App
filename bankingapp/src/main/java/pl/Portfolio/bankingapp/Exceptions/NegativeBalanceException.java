package pl.Portfolio.bankingapp.Exceptions;

public class NegativeBalanceException extends RuntimeException {

    public NegativeBalanceException(String message) {
        super(message);
    }
}
