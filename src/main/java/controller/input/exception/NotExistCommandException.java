package controller.input.exception;

import java.io.IOException;

public class NotExistCommandException extends IOException {

    public NotExistCommandException(String message) {
        super(message);
    }
}
