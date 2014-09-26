package com.jpp.roundedimages;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyAdapter extends FragmentPagerAdapter {


    private static final int COUNT = 3;

    public MyAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int id) {
        switch (id) {
        case 0:
            return FragmentJhon.newInstance();
        case 1:
            return FragmentChristina.newInstance();
        case 2:
            return FragmentAndrew.newInstance();
        default:
            return null;

        }
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    
    
}
