package com.sync.service.library.receiver;

import android.os.Bundle;

import com.sync.service.library.SyncStatus;

/**
 * Interface that defines the signature that any back-end client that needs to
 * execute an asynchronous request MUST respect.
 * 
 * @author Juan P. Peretti 
 * 
 */
public interface AsyncReceiver {


    /**
     * Called each time that some progress is communicated by the asynchronous
     * controller.
     * 
     * @param status
     *            - the {@link AsyncStatus} to communicate to this receiver
     * @param resultData
     *            - a {@link Bundle} containing any extra data needed.
     */
    public void onReceiveResult(SyncStatus status, Bundle resultData);

}
