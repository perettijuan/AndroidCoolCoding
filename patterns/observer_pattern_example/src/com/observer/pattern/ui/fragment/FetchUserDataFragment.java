package com.observer.pattern.ui.fragment;

import com.observer.pattern.R;
import com.observer.pattern.services.ActionController;
import com.observer.pattern.services.IActionStatusListener;
import com.observer.pattern.services.MonitoredAction;
import com.observer.pattern.services.ServerAccessMock;
import com.observer.pattern.services.ActionController.State;
import com.observer.pattern.ui.INavigationListener;
import com.observer.pattern.ui.Section;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ProgressBar;

public class FetchUserDataFragment extends Fragment implements OnClickListener, IActionStatusListener<Bundle> {


    public static final String TAG = FetchUserDataFragment.class.getName();

    private INavigationListener mListener;
    private Button btnFetch;
    private ProgressBar pgFetch;

    public FetchUserDataFragment() {

    }

    public static FetchUserDataFragment newInstance(INavigationListener listener) {
        FetchUserDataFragment fr = new FetchUserDataFragment();
        fr.mListener = listener;
        return fr;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fView = inflater.inflate(R.layout.fetch_user_fragment, container, false);
        btnFetch = (Button) fView.findViewById(R.id.btn_fetch);
        btnFetch.setOnClickListener(this);
        pgFetch = (ProgressBar) fView.findViewById(R.id.pg_fetch);
        return fView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionController.ACTION_CONTROLLER.registerListener(this, MonitoredAction.ACTION_FETCH_USER_INFO);
    }

    @Override
    public void onPause() {
        super.onPause();
        ActionController.ACTION_CONTROLLER.unregisterListener(this);
    }

    @Override
    public void actionStatusChange(MonitoredAction action, State actionStatus, Bundle extraData) {
        switch (action) {
        case ACTION_FETCH_USER_INFO:
            if (State.STATE_IN_PROGRESS.equals(actionStatus)) {
                pgFetch.setVisibility(View.VISIBLE);
                btnFetch.setVisibility(View.GONE);
            } else if (State.STATE_DONE.equals(actionStatus)) {
                if (mListener != null) {
                    mListener.showSection(Section.UPDATE_USER_DATA);
                }
            }
            break;

        default:
            break;
        }
    }

    @Override
    public void onClick(View v) {
        ServerAccessMock mock = new ServerAccessMock();
        mock.fetchUserinfo();
    }

}
