package model.exceptions;

import java.time.LocalDate;

public class BusinessException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2693765591124531057L;

	//Constructors Overload
	public BusinessException() {
		super();
	}

	public BusinessException(String messageThrow) {
		super(messageThrow);
	}

	public BusinessException(String messageThrow, Throwable cause) {
		super(messageThrow, cause);
	}

}
