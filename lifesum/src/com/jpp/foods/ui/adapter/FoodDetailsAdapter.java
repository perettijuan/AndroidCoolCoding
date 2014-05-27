package com.jpp.foods.ui.adapter;

import com.jpp.foods.ui.fragment.FoodDetailsFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Adapter used to show different fragments in a view pager view.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class FoodDetailsAdapter extends FragmentStatePagerAdapter {


    private int mCount;

    public FoodDetailsAdapter(FragmentManager fm, int count) {
        super(fm);
        mCount = count;
    }

    @Override
    public Fragment getItem(int position) {
        /*
         * since the cursor that contains the data is a FoodsCursor, the
         * position matches with the id used to identify a row.
         */
        return FoodDetailsFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return mCount;
    }

}
