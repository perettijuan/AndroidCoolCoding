package com.jpp.androidchallenge.background;

/**
 * Interface that defines the contract that any interested in handling
 * events from a BackgroundTaskExecutor.
 */
public interface IBackgroundExecutionListener {

    /**
     * Called when the task in successfully completed.
     */
    public void onSuccess();

    /**
     * Called when an error is detected.
     */
    public void onError();

}
