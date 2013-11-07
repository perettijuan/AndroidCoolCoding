package com.jpp.animations.ui.fragment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.jpp.animations.R;
import com.jpp.animations.ui.ISldierMenuSelectionListener;
import com.jpp.animations.ui.adapter.SliderMenuAdapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

public class SliderMenuFragment extends Fragment {


    public final static String TAG = SliderMenuFragment.class.getName();

    private static final String[] HEADERS = { "Simple Animations" };
    private final static String[] SIMPLE_ANIMATIONS = { "Fade" };

    private SliderMenuAdapter mAdapter;
    private ExpandableListView lvDrawerMenu;

    public static SliderMenuFragment newInstance(ISldierMenuSelectionListener listener) {
        SliderMenuFragment instance = new SliderMenuFragment();
        return instance;
    }

    public SliderMenuFragment() {

    }
    
    
    public void changeListener(ISldierMenuSelectionListener listener) {
        
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        List<String> headers = Arrays.asList(HEADERS);
        HashMap<String, List<String>> items = new HashMap<String, List<String>>();
        List<String> simpleAnimations = Arrays.asList(SIMPLE_ANIMATIONS);
        items.put(headers.get(0), simpleAnimations);

        View fView = inflater.inflate(R.layout.slider_menu_fragment, container, false);
        lvDrawerMenu = (ExpandableListView) fView.findViewById(R.id.lv_drawer_menu);
        mAdapter = new SliderMenuAdapter(getActivity(), headers, items);
        lvDrawerMenu.setAdapter(mAdapter);

        return fView;
    }
}
