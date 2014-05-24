package com.jpp.foods.ui;

import android.app.Activity;
import android.os.Bundle;

import com.jpp.foods.R;
import com.jpp.foods.ui.fragment.FoodsFragment;

/**
 * Activity that represents the Home screen
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class HomeScreen extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        if (savedInstanceState == null) {
            FoodsFragment fragment = FoodsFragment.newInstance();
            android.app.FragmentTransaction tr = getFragmentManager().beginTransaction();
            tr.add(R.id.home_fragment_container, fragment, FoodsFragment.TAG);
            tr.commit();
        }
    }

}
