package com.birol.web.error;

public class EmployeeNotFoundException extends RuntimeException {

	 private static final long serialVersionUID = 5861310537366287163L;

	    public EmployeeNotFoundException() {
	        super();
	    }

	    public EmployeeNotFoundException(final String message, final Throwable cause) {
	        super(message, cause);
	    }

	    public EmployeeNotFoundException(final String message) {
	        super(message);
	    }

	    public EmployeeNotFoundException(final Throwable cause) {
	        super(cause);
	    }
}
