package rmm.exceptions;

public class InvalidRequestException extends RuntimeException {
    private static final long serialVersionUID = -8122477275831218161L;

    public InvalidRequestException(String message) {
        super(message);
    }
}
