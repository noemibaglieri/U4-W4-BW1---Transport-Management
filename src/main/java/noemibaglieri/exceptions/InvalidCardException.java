package noemibaglieri.exceptions;

public class InvalidCardException extends RuntimeException {
    public InvalidCardException(Long cardId) {
        super("Card with ID " + cardId + " is either inactive or expired.");
    }
}
