package noemibaglieri.exceptions;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(long id) {
        super("Ticket with id" + id + "was not found" );
    }
}
