/**
 * 
 */
package com.variamos.core.exceptions;

/**
 * Represents a controlled technical exception
 * @author Lufe
 *
 */
public class TechnicalException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public TechnicalException() {
		
	}

	/**
	 * @param arg0
	 */
	public TechnicalException(String arg0) {
		super(arg0);
		
	}

	/**
	 * @param arg0
	 */
	public TechnicalException(Throwable arg0) {
		super(arg0);
		
	}

	/**
	 * @param arg0
	 * @param arg1
	 */
	public TechnicalException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public TechnicalException(String arg0, Throwable arg1, boolean arg2,
			boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		
	}

}
