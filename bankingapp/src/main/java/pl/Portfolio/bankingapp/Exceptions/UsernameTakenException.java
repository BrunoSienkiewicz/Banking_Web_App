package pl.Portfolio.bankingapp.Exceptions;

public class UsernameTakenException extends RuntimeException{

        public UsernameTakenException(String message) {
            super(message);
        }
}
