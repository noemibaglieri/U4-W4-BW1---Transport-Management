package noemibaglieri.exceptions;

public class VendorUnavailableException extends RuntimeException {
    public VendorUnavailableException(String name) {
        super("Vendor '" + name + "' is not available at this time.");
    }
}