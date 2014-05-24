package com.jpp.foods;

import android.util.Log;

/**
 * Application's logger class. It replaces {@link Log} by wrapping around it in
 * order to avoid logging values after releasing the product.
 * 
 * @author Juan P. Peretti (juan.peretti@tallertechnologies.com)
 * 
 */
public final class Logger {


    /**
     * Log message in debug mode.
     * 
     * @param message
     *            - the messsage to log using the tag
     *            {@link Constants#FOR_TESTING}
     */
    public static void debug(String message) {
        if (Constants.FOR_TESTING) {
            Log.d(Constants.APP_LOG_TAG, message);
        }
    }

    /**
     * Logs the message received in an error fashion.
     * 
     * @param message
     *            - the messsage to log using the tag
     *            {@link Constants#FOR_TESTING}
     */
    public static void error(String msg) {
        if (Constants.FOR_TESTING) {
            Log.e(Constants.APP_LOG_TAG, msg);
        }
    }

    /**
     * Logs the message received in a warning fashion.
     * 
     * @param message
     *            - the messsage to log using the tag
     *            {@link Constants#FOR_TESTING}
     */
    public static void warning(String msg) {
        if (Constants.FOR_TESTING) {
            Log.w(Constants.APP_LOG_TAG, msg);
        }
    }

    /**
     * Logs the message in debug mode appending the host name to the tag
     * {@link Constants#FOR_TESTING}
     * 
     * @param message
     *            - the messsage to log using the tag
     *            {@link Constants#FOR_TESTING}
     * @param host
     *            - the name of the object that hots the message
     */
    public static void debug(String message, Object host) {
        if (Constants.FOR_TESTING) {
            Log.d(Constants.APP_LOG_TAG + Constants.SLASH + host.getClass().getSimpleName(), message);
        }
    }

    /**
     * Logs the message in error mode appending the host name to the tag
     * {@link Constants#FOR_TESTING}
     * 
     * @param message
     *            - the messsage to log using the tag
     *            {@link Constants#FOR_TESTING}
     * @param host
     *            - the name of the object that hots the message
     */
    public static void error(String message, Object host) {
        if (Constants.FOR_TESTING) {
            Log.e(Constants.APP_LOG_TAG + Constants.SLASH + host.getClass().getSimpleName(), message);
        }
    }

    /**
     * Logs the message in warning mode appending the host name to the tag
     * {@link Constants#FOR_TESTING}
     * 
     * @param message
     *            - the messsage to log using the tag
     *            {@link Constants#FOR_TESTING}
     * @param host
     *            - the name of the object that hots the message
     */
    public static void warning(String message, Object host) {
        if (Constants.FOR_TESTING) {
            Log.w(Constants.APP_LOG_TAG + Constants.SLASH + host.getClass().getSimpleName(), message);
        }
    }

    /**
     * Logs the message in debug mode appending the feature name name to the tag
     * {@link Constants#FOR_TESTING}
     * 
     * @param message
     *            - the messsage to log using the tag
     *            {@link Constants#FOR_TESTING}
     * @param featureName
     *            - the name of the feature implementing the log
     */
    public static void debug(String message, String featureName) {
        if (Constants.FOR_TESTING) {
            Log.d(Constants.APP_LOG_TAG + Constants.SLASH + featureName, message);
        }
    }

    /**
     * Logs the message in error mode appending the feature name name to the tag
     * {@link Constants#FOR_TESTING}
     * 
     * @param message
     *            - the messsage to log using the tag
     *            {@link Constants#FOR_TESTING}
     * @param featureName
     *            - the name of the feature implementing the log
     */
    public static void error(String message, String featureName) {
        if (Constants.FOR_TESTING) {
            Log.e(Constants.APP_LOG_TAG + Constants.SLASH + featureName, message);
        }
    }

    /**
     * Logs the message in debug warning appending the feature name name to the
     * tag {@link Constants#FOR_TESTING}
     * 
     * @param message
     *            - the messsage to log using the tag
     *            {@link Constants#FOR_TESTING}
     * @param featureName
     *            - the name of the feature implementing the log
     */
    public static void warning(String message, String featureName) {
        if (Constants.FOR_TESTING) {
            Log.w(Constants.APP_LOG_TAG + Constants.SLASH + featureName, message);
        }
    }

}
