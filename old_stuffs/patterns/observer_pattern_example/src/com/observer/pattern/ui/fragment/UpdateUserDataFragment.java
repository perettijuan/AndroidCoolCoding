package com.observer.pattern.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.observer.pattern.R;
import com.observer.pattern.services.ActionController;
import com.observer.pattern.services.ActionController.State;
import com.observer.pattern.services.IActionStatusListener;
import com.observer.pattern.services.MonitoredAction;
import com.observer.pattern.services.ServerAccessMock;
import com.observer.pattern.ui.INavigationListener;

public class UpdateUserDataFragment extends Fragment implements OnClickListener, IActionStatusListener<Bundle> {


    public static final String TAG = UpdateUserDataFragment.class.getName();

    private INavigationListener mListener;
    private Button btnUpdate;
    private ProgressBar pgUpdate;

    public UpdateUserDataFragment() {

    }

    public static UpdateUserDataFragment newInstance(INavigationListener listener) {
        UpdateUserDataFragment fr = new UpdateUserDataFragment();
        fr.mListener = listener;
        return fr;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fView = inflater.inflate(R.layout.update_user_data_fragment, container, false);
        btnUpdate = (Button) fView.findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(this);
        pgUpdate = (ProgressBar) fView.findViewById(R.id.pg_update);
        return fView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionController.ACTION_CONTROLLER.registerListener(this, MonitoredAction.ACTION_UPDATE_USER_INFO);
    }

    @Override
    public void onPause() {
        super.onPause();
        ActionController.ACTION_CONTROLLER.unregisterListener(this);
    }

    @Override
    public void actionStatusChange(MonitoredAction action, State actionStatus, Bundle extraData) {
        switch (action) {
        case ACTION_UPDATE_USER_INFO:
            if (State.STATE_IN_PROGRESS.equals(actionStatus)) {
                pgUpdate.setVisibility(View.VISIBLE);
                btnUpdate.setVisibility(View.GONE);
            } else if (State.STATE_DONE.equals(actionStatus)) {
                if (mListener != null) {
                    Toast.makeText(getActivity(), "Demo is over!", Toast.LENGTH_LONG).show();
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
        mock.updateUserProfile();
    }

}
