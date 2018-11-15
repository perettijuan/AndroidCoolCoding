package com.sync.service.library;

import java.io.Serializable;

import android.annotation.SuppressLint;
import android.os.Parcelable;

/**
 * Interface that represents a request to {@link SyncService}<br>
 * The logical implementation for this interface is to have a enum that
 * implements this interface and provide the information needed to know the type
 * of request being made.
 * 
 * @author Juan P. Peretti
 * 
 */
@SuppressLint("ParcelCreator")
public interface IServiceRequest extends Serializable {


    /**
     * Determines whether this {@link IServiceRequest} can run in parallel with
     * another or not.
     * 
     * @return - true if it can run in parallel, false any other case.
     */
    public boolean canRunInParallel();

}
