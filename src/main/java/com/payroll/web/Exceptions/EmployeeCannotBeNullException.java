package com.payroll.web.Exceptions;

public class EmployeeCannotBeNullException extends Exception{
    public EmployeeCannotBeNullException() {
    }

    public EmployeeCannotBeNullException(String message) {
        super(message);
    }

    public EmployeeCannotBeNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmployeeCannotBeNullException(Throwable cause) {
        super(cause);
    }
}
