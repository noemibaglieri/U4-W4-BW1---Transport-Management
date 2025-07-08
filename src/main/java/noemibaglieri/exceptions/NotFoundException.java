package noemibaglieri.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException(long id) {
        super("The route "+ id + " was not found!");
    }
}
