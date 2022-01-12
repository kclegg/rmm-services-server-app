package rmm.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 3372618741484444490L;

    public NotFoundException(String message) {
        super(message);
    }

    // TODO: try both
    public NotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
