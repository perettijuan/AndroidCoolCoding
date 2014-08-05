package com.sync.service.library;

import android.content.Context;
import android.os.Bundle;

/**
 * Executor class to follow the command pattern <br>
 * All the interactions with the backend will be handled, in the end, for a
 * sub-class of this class.
 * 
 * @author Juan P. Peretti
 * 
 */
public abstract class Executor {

	// Context to be used in back-end executions
	protected Context mContext;

	// a Bundle to use as response container
	protected Bundle mReturnValues;

	public Executor() {
	}

	/**
	 * Called each time that an action with the backend is going to be started.
	 * 
	 * @param params
	 *            - a map containing any extra parameter needed.
	 * @param context
	 *            - the context in wich the executor will run.
	 * @return - a {@link Bundle} containing any extra result data needed.
	 */
	protected Bundle execute(Bundle params, Context context) throws ServiceException {
		mContext = context;

		// parse the parameters
		parseParams(params);

		// prepare the URL for the request
		prepareForExecution();

		mReturnValues = new Bundle();

		// execute the request
		return executeImpl();
	}

	/**
	 * Parses the parameters received in the {@link Bundle} arguments into the
	 * ones needed by the request. It is called each time that the executor will
	 * start, just before {@link #executeImpl()}
	 * 
	 * @param params
	 *            - a map containing the data to use as parameters
	 */
	protected abstract void parseParams(Bundle params) throws ServiceException;

	/**
	 * Prepares the data that is going to be use to execute a back-end interaction
	 */
	protected abstract void prepareForExecution() throws ServiceException;

	/**
	 * All the executors added will need to implement the logic under this
	 * method
	 * 
	 * @return - a {@link Bundle} containing any extra result data needed.
	 */
	protected abstract Bundle executeImpl() throws ServiceException;

}
