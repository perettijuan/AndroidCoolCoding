package com.observer.pattern.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import com.observer.pattern.R;
import com.observer.pattern.ui.fragment.FetchUserDataFragment;
import com.observer.pattern.ui.fragment.LoginFragment;
import com.observer.pattern.ui.fragment.UpdateUserDataFragment;

public class HomeScreen extends FragmentActivity implements INavigationListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        showSection(Section.LOGIN);
    }

    @Override
    public void showSection(Section section) {
        Fragment fr = null;
        switch (section) {
        case LOGIN:
            fr = LoginFragment.newInstance(this);
            break;
        case FETCH_USER_DATA:
            fr = FetchUserDataFragment.newInstance(this);
            break;
        case UPDATE_USER_DATA:
            fr = UpdateUserDataFragment.newInstance(this);
            break;
        }

        if (fr != null) {
            FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
            tr.replace(R.id.fl_fragment_container, fr, section.getTag());
            tr.commit();
        }
    }

}
