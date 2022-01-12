package common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidRecordRequest extends RuntimeException {
    private static final long serialVersionUID = -4282124437855045821L;

    public InvalidRecordRequest(String message) {
        super(message);
    }

    // TODO: try both
    public InvalidRecordRequest(String message, Throwable throwable) {
        super(message, throwable);
    }
}
