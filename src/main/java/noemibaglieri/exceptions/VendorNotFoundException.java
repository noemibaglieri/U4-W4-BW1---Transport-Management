package noemibaglieri.exceptions;

public class VendorNotFoundException extends RuntimeException {
    public VendorNotFoundException(Long id) {
        super("I can't find this vendor: (" + id + "). Try again");
    }
}
