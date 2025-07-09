package noemibaglieri.exceptions;

public class PassNotFoundException extends RuntimeException {
    public PassNotFoundException(long id) {
        super("The pass with ID " + id + " was not found!");
    }
}
