package com.payroll.web.Exceptions;

public class IdCannotBeNullException extends Exception {
    public IdCannotBeNullException() {
    }

    public IdCannotBeNullException(String message) {
        super(message);
    }

    public IdCannotBeNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public IdCannotBeNullException(Throwable cause) {
        super(cause);
    }
}
