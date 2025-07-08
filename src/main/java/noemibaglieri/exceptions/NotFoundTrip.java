package noemibaglieri.exceptions;

public class NotFoundTrip extends RuntimeException {
    public NotFoundTrip(long tripId) {
        super("The Vehicle Trip "+tripId+ "was not found!");
    }
}
