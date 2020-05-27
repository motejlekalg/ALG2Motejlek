package orderingsystem.utils;

/**
 * An exception thrown when an unknown item code is passed as a parameter.
 *
 * @author Martin Motejlek
 */
public class UnsupportedExtensionException extends IllegalArgumentException {

    public UnsupportedExtensionException() {
    }

    public UnsupportedExtensionException(String message) {
        super(message);
    }

}
