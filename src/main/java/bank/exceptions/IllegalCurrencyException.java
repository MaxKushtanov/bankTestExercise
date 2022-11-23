package bank.exceptions;

public class IllegalCurrencyException extends RuntimeException {
    public IllegalCurrencyException(String message) {
        super(message);
    }
}
