package pl.Portfolio.bankingapp.Exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException (String message) {
        super(message);
    }
}
