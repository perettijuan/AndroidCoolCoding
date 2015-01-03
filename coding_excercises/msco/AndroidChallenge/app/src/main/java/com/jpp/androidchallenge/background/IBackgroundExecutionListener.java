package com.jpp.androidchallenge.background;

import android.content.Context;

/**
 * An interface used to notify events about a background job.
 */
public interface IBackgroundExecutionListener {

    public void onSuccess();

    public void onError();

}
