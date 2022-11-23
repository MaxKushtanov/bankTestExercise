package bank.exceptions;

public class IllegalAmountException extends RuntimeException {
    public IllegalAmountException(String message) {
        super(message);
    }
}
