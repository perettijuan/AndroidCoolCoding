package com.sync.service.library.receiver;

import android.app.Service;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

import com.sync.service.library.SyncStatus;

/**
 * Proxy to {@link ResultReceiver} that offers a listener (
 * {@link AsyncReceiver} ) that can be detached. <br>
 * This is useful when the listening element (i.e: an Activity or a Fragment) is
 * swapped in the middle of an interaction with a {@link Service} and the
 * {@link Service} needs to send some callbacks to the listening element.
 * 
 * @author Juan P. Peretti 
 * 
 */
public class DetachableResultReceiver extends ResultReceiver {


    private AsyncReceiver mReceiver;
    private boolean mIsRunning;

    public DetachableResultReceiver(Handler handler) {
        super(handler);
    }

    /**
     * Called clear the receiver
     */
    public void clearReceiver() {
        mReceiver = null;
    }

    /**
     * Called to swap the receiver for a new one
     * 
     * @param receiver
     *            - the {@link AsyncReceiver} to swap for.
     */
    public void setReceiver(AsyncReceiver receiver) {
        mReceiver = receiver;
    }

    public boolean isRunning() {
        return (mReceiver != null && mIsRunning);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
        	SyncStatus status = SyncStatus.getFromValue(resultCode);
            mIsRunning = SyncStatus.STATUS_RUNNING.equals(status);
            mReceiver.onReceiveResult(status, resultData);
        } else {
            mIsRunning = false;
        }
    }

}
