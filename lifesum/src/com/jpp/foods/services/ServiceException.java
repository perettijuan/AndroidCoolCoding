package com.jpp.foods.services;

import android.content.Context;

import com.jpp.foods.R;

/**
 * {@link Exception} thrown by when there is some problem with the backend
 * interaction
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class ServiceException extends Exception {

	private static final long serialVersionUID = -7943535917778485357L;
	protected final ApiMessageEnum mMessage;

	protected ServiceException() {
		mMessage = null;
	}

	public ServiceException(ApiMessageEnum message) {
		super();
		mMessage = message;
	}

	public ServiceException(ApiMessageEnum message, String customMessage) {
		super(customMessage);
		mMessage = message;
	}

	public ServiceException(Throwable ex, ApiMessageEnum message) {
		super(ex);
		mMessage = message;
	}

	public ApiMessageEnum getMessageEnum() {
		return mMessage;
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

	/**
	 * Get extra exception data. This allows subclasses to return extra data in
	 * case it's necessary
	 * 
	 * @return
	 */
	public Object getExtraData() {
		return null;
	}

	/**
	 * Get the resolved message
	 * 
	 * @param context
	 * @param params
	 * @return
	 */
	public String getResolvedMessage(Context context, Object... params) {
		if (mMessage != null) {
			String errorMessage = "";
			for (int i = 0; i < params.length; i++) {
				errorMessage = " " + params[i].toString();
			}
			return context.getString(mMessage.getMessageRes(), "error",
					errorMessage);
		} else {
			return context.getString(R.string.error_general);
		}
	}

}
