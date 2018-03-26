package com.gcu.edu.exception;

/**
 * Code retrieved from Mark Reha's Cloud Computing code
 * Runtime Exception thrown Database Exception
 *
 * @author Mark Reha
 * @version 1
 */
public class DAOException extends RuntimeException 
{
    static final long serialVersionUID = 0;

    public DAOException() {
		// TODO Auto-generated constructor stub
	}

	public DAOException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public DAOException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public DAOException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public DAOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}
}