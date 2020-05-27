package orderingsystem.utils;

/**
 * An exception thrown when an unsupported extension is passed to a
 * file-handling function.
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
