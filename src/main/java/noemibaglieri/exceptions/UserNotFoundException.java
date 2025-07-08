package noemibaglieri.exceptions;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(long id) {
        super("I can't find this user: (" + id + "). Try again");
    }
}