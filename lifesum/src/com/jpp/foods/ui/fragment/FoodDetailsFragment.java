package com.jpp.foods.ui.fragment;

import android.app.Fragment;

/**
 * Fragment that shows the details of a selected Food.
 * 
 * @author Juan P. Peretti (peretti.juan@gmail.com)
 * 
 */
public class FoodDetailsFragment extends Fragment {


    public static final String TAG = FoodDetailsFragment.class.getName();

    /**
     * Factory method
     */
    public static FoodDetailsFragment newInstance() {
        return new FoodDetailsFragment();
    }

    /**
     * Empty class constructor.
     */
    public FoodDetailsFragment() {

    }

}
