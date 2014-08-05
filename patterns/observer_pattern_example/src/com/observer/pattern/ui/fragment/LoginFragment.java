package com.observer.pattern.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.observer.pattern.R;
import com.observer.pattern.services.ActionController;
import com.observer.pattern.services.ActionController.State;
import com.observer.pattern.services.IActionStatusListener;
import com.observer.pattern.services.MonitoredAction;
import com.observer.pattern.services.ServerAccessMock;
import com.observer.pattern.ui.INavigationListener;
import com.observer.pattern.ui.Section;

public class LoginFragment extends Fragment implements OnClickListener, IActionStatusListener<Bundle> {


    public static final String TAG = LoginFragment.class.getName();

    private INavigationListener mListener;
    private Button btnLogin;
    private ProgressBar pgLogin;

    public LoginFragment() {

    }

    public static LoginFragment newInstance(INavigationListener listener) {
        LoginFragment fr = new LoginFragment();
        fr.mListener = listener;
        return fr;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fView = inflater.inflate(R.layout.login_fragment, container, false);
        btnLogin = (Button) fView.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        pgLogin = (ProgressBar) fView.findViewById(R.id.pg_login);
        return fView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionController.ACTION_CONTROLLER.registerListener(this, MonitoredAction.ACTION_LOGIN);
    }

    @Override
    public void onPause() {
        super.onPause();
        ActionController.ACTION_CONTROLLER.unregisterListener(this);
    }

    @Override
    public void actionStatusChange(MonitoredAction action, State actionStatus, Bundle extraData) {
        switch (action) {
        case ACTION_LOGIN:
            if (State.STATE_IN_PROGRESS.equals(actionStatus)) {
                pgLogin.setVisibility(View.VISIBLE);
                btnLogin.setVisibility(View.GONE);
            } else if (State.STATE_DONE.equals(actionStatus)) {
                if (mListener != null) {
                    mListener.showSection(Section.FETCH_USER_DATA);
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
        mock.login();
    }

}
