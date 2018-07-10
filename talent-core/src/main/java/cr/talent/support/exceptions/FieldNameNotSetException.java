package cr.talent.support.exceptions;

/**
 * Runtime exception thrown when {@link cr.talent.support.flexjson.AbstractInlineTransformer} implementations try to write
 * the field name without setting it first.
 *
 * @author Fabián Roberto Leandro
 */
public class FieldNameNotSetException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public FieldNameNotSetException() {
    }

    /**
     * Creates a new exception with the specified message
     * @param message the message to display
     */
    public FieldNameNotSetException(String message) {
        super(message);
    }

    /**
     * Creates a new exception with the specified wrapped exception
     * @param cause the cause of the exception
     */
    public FieldNameNotSetException(Throwable cause) {
        super(cause);
    }

    /**
     * Creates a new exception with the specified message and wrapped exception
     * @param message the message to display
     * @param cause the cause of the exception
     */
    public FieldNameNotSetException(String message, Throwable cause) {
        super(message, cause);
    }
}
