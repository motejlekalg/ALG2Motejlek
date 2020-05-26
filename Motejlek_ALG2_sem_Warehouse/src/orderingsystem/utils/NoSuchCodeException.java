package orderingsystem.utils;

/**
 *
 * @author Martin Motejlek
 */
public class NoSuchCodeException extends RuntimeException {

    public NoSuchCodeException() {
    }

    public NoSuchCodeException(String message) {
        super(message);
    }
    
}
