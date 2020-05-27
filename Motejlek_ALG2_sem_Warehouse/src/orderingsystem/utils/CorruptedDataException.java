package orderingsystem.utils;

/**
 * An exception thrown when a file cannot be parsed.
 *
 * @author Martin Motejlek
 */
public class CorruptedDataException extends RuntimeException {

    public CorruptedDataException() {
    }

    public CorruptedDataException(String message) {
        super(message);
    }

}
