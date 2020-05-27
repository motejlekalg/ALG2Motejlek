package orderingsystem.utils;

/**
 *
 * @author Martin Motejlek
 */
public class CorruptedFileException extends RuntimeException {

    public CorruptedFileException() {
    }

    public CorruptedFileException(String message) {
        super(message);
    }
    
}
