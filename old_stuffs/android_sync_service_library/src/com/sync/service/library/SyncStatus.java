package com.sync.service.library;

/**
 * An enum that defines all the possible states that an asynchronous request
 * sent to the {@link SyncService} can go thru.
 * 
 * @author Juan P. Peretti
 * 
 */
public enum SyncStatus {

    // An invalid status
    STATUS_INVALID(-1),
    // The request is running
    STATUS_RUNNING(0),
    // Some error occurred while executing
    STATUS_ERROR(1),
    // The request has finished.
    STATUS_FINISHED(2);


    private int mValue;

    private SyncStatus(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }

    /**
     * Returns an {@link AsyncStatus} given the integer value.
     * 
     * @param value
     *            - the value of the {@link AsyncStatus} to retrieve
     * @return - the {@link AsyncStatus} founded. Note that
     *         {@link AsyncStatus#STATUS_INVALID} will be returned if no
     *         matching is found.
     */
    public static SyncStatus getFromValue(int value) {
        SyncStatus status = STATUS_INVALID;
        switch (value) {
        case 0:
            status = STATUS_RUNNING;
            break;
        case 1:
            status = STATUS_ERROR;
            break;
        case 2:
            status = STATUS_FINISHED;
            break;
        }
        return status;
    }
}
