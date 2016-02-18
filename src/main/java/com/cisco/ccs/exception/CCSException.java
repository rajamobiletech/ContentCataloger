/**
 * 
 */
package com.cisco.ccs.exception;

import java.io.Serializable;

/**
 * File:PLEException.java Package:com.cisco.ple.exception
 * 
 * @author Vinod
 */
public class CCSException extends Exception implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;
    /**
     * Property :exceptionMessage Description: exception message.
     */
    protected String exceptionMessage;

    /**
     * Constructor: PLEException() Description: Instantiates a new pLE
     * exception.
     */
    public CCSException() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Constructor: PLEException(String message) Description: Instantiates a new
     * pLE exception.
     * 
     * @param message
     *            : the message
     */
    public CCSException(String message) {
        // TODO Auto-generated constructor stub
        this.exceptionMessage = message;
    }

    /**
     * Constructor: PLEException(Throwable cause) Description: Instantiates a
     * new pLE exception.
     * 
     * @param cause
     *            : the cause
     */
    public CCSException(Throwable cause) {
        // TODO Auto-generated constructor stub
        this.initCause(cause);
    }

    /**
     * Constructor: PLEException(String message, Throwable cause) Description:
     * Instantiates a new pLE exception.
     * 
     * @param message
     *            : the message
     * @param cause
     *            : the cause
     */
    public CCSException(String message, Throwable cause) {
        // TODO Auto-generated constructor stub
        this.exceptionMessage = message;
        this.initCause(cause);

    }

    /**
     * Method: void setCause(Throwable cause) Description: Sets the cause.
     * 
     * @param cause
     *            : the new cause
     */
    public void setCause(Throwable cause) {
        this.initCause(cause);
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        return this.exceptionMessage;
    }

    /*
     * (non-Javadoc)
     * @see java.lang.Throwable#toString()
     */
    @Override
    public String toString() {
        String s = getClass().getName();
        return s + ": " + this.exceptionMessage;
    }

}
