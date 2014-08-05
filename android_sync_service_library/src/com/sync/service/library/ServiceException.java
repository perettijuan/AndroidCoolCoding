package com.sync.service.library;


/**
 * {@link Exception} thrown by when there is some problem with the backend
 * interaction
 * 
 * @author Juan P. Peretti 
 * 
 */
public class ServiceException extends Exception {


    private static final long serialVersionUID = -7943535917778485357L;
    protected final IServiceException mMessage;

    protected ServiceException() {
        mMessage = null;
    }

    public ServiceException(IServiceException message) {
		super(message.getExceptionMsge());
        mMessage = message;
    }

    public ServiceException(IServiceException message, String customMessage) {
        super(customMessage);
        mMessage = message;
    }

    public ServiceException(Throwable ex, IServiceException message) {
        super(ex);
        mMessage = message;
    }

    @Override
    public boolean equals(Object o) {
        boolean equals = false;
        if (o instanceof ServiceException) {
            equals = ((ServiceException) o).mMessage.equals(mMessage);
        }
        return equals;
    }

    @Override
    public int hashCode() {
        return 0;
    }
}