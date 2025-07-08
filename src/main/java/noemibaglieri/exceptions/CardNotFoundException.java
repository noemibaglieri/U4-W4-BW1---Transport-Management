package noemibaglieri.exceptions;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException(long id) {
        super("I can't find this transport card: (" + id + "). Try again");
    }
}
