package com.jpp.codingex.ui;

import com.jpp.codingex.R;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainScreen extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        Fragment mainFragment = getFragmentManager().findFragmentByTag(MainFragment.TAG);
        if (mainFragment == null) {
            mainFragment = MainFragment.newInstance();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.add(R.id.fragment_container, mainFragment, MainFragment.TAG);
            transaction.commit();
        }

    }

}
