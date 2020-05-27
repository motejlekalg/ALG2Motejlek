package orderingsystem.utils;

/**
 * An exception thrown when a file contains corrupted data or a class is
 * attempted to be initialised with invalid data.
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
