package com.sync.service.library.receiver;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;

import com.sync.service.library.IServiceRequest;
import com.sync.service.library.ServiceException;
import com.sync.service.library.SyncConstants;
import com.sync.service.library.SyncService;
import com.sync.service.library.SyncStatus;

/**
 * An abstract fragment that defines common behavior for all fragments that will
 * execute some sync action with the back-end via {@link SyncService}.
 * 
 * @author Juan P. Peretti
 * 
 */
public abstract class SyncFragment extends Fragment implements AsyncReceiver {

	private static final String KEY_RECEIVER = "receiver";

	// the receiver to be pass to the sync-service
	protected DetachableResultReceiver mReceiver;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (savedInstanceState != null
				&& savedInstanceState.getParcelable(KEY_RECEIVER) != null) {
			try {
				mReceiver = savedInstanceState.getParcelable(KEY_RECEIVER);
			} catch (Exception e) {
				mReceiver = new DetachableResultReceiver(new Handler());
			}

		} else {
			mReceiver = new DetachableResultReceiver(new Handler());
		}
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void onReceiveResult(SyncStatus status, Bundle resultData) {
		IServiceRequest request = null;
		if (resultData != null) {
			request = resultData
					.getParcelable(SyncConstants.EXTRA_SERVICE_REQUEST_ID);
		}
		switch (status) {
		case STATUS_RUNNING:
			onPublishProgress(request);
			break;
		case STATUS_FINISHED:
			onPublishResults(request, resultData);
			break;
		case STATUS_ERROR:
			ServiceException ex = (ServiceException) resultData
					.getSerializable(SyncConstants.EXTRA_SERVICE_EXCEPTION);
			onPublishError(request, ex);
			break;
		}
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putParcelable(KEY_RECEIVER, mReceiver);
	}

	@Override
	public void onResume() {
		super.onResume();
		mReceiver.setReceiver(this);
	}

	@Override
	public void onPause() {
		super.onPause();
		mReceiver.clearReceiver();
		if (shouldStopBackendExecutionOnPause()) {
			stopServiceExecution();
		}
	}

	/**
	 * Stops the exeuction of any interaction with the server
	 */
	protected void stopServiceExecution() {
		// force stop the SyncService if running
		Intent broadcastIntent = new Intent(SyncService.INTENT_ACTION);
		LocalBroadcastManager
				.getInstance(getActivity().getApplicationContext())
				.sendBroadcast(broadcastIntent);
	}

	/**
	 * @return - true if should stop any server interaction in onPause(), false
	 *         any other case.
	 */
	protected abstract boolean shouldStopBackendExecutionOnPause();

	/**
	 * Called each time that some progress needs to be published. If a sub-class
	 * of this class wishes to publish iit's own progress, needs to override
	 * this method
	 * 
	 * @param request
	 *            - the {@link ServicesRequest} that identifies the request in
	 *            progress.
	 */
	protected abstract void onPublishProgress(IServiceRequest request);

	/**
	 * Called to publish some progress that has finished.
	 * 
	 * @param request
	 *            - the {@link ServicesRequest} that has finished.
	 * @param resultData
	 *            - a map containing any extra data retrieved from the back-end.
	 */
	protected abstract void onPublishResults(IServiceRequest request,
			Bundle resultData);

	/**
	 * Called to publish some error that occurred while syncing. If a sub-class
	 * wishes to provide an specific error message, needs to override this
	 * method.
	 * 
	 * @param request
	 *            - the {@link ServicesRequest} that identifies the process that
	 *            throws the error.
	 * @param ex
	 *            - the {@link ServiceException} that contains the message to
	 *            shown.
	 */
	protected abstract void onPublishError(IServiceRequest request,
			ServiceException ex);

}
