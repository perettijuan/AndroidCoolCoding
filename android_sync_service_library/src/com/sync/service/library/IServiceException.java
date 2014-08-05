package com.sync.service.library;

/**
 * Interface that represents a exception from {@link Executor} subclass<br>
 * The logical implementation for this interface is to have a enum that
 * implements this interface and provide the information needed to know the type
 * of exception being made.
 * 
 * @author Juan P. Peretti
 */
public interface IServiceException {

	/**
	 * Set the text to recognize what type of {@link ServiceException} is.
	 * 
	 * @return - the String for the exception
	 */
	public String getExceptionMsge();

}
