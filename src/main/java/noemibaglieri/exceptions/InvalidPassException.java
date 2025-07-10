package noemibaglieri.exceptions;


public class InvalidPassException extends RuntimeException {
    public InvalidPassException(Long id) {
        super("The pass with ID " + id + " is expired or invalid.");
    }
}
