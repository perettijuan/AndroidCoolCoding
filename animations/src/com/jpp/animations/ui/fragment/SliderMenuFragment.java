package com.jpp.animations.ui.fragment;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;

import com.jpp.animations.AnimationGroups;
import com.jpp.animations.Animations;
import com.jpp.animations.R;
import com.jpp.animations.ui.ISldierMenuSelectionListener;
import com.jpp.animations.ui.adapter.SliderMenuAdapter;

public class SliderMenuFragment extends Fragment {


    public final static String TAG = SliderMenuFragment.class.getName();

    private SliderMenuAdapter mAdapter;
    private ExpandableListView lvDrawerMenu;
    private ISldierMenuSelectionListener mListener;

    public static SliderMenuFragment newInstance(ISldierMenuSelectionListener listener) {
        SliderMenuFragment instance = new SliderMenuFragment();
        instance.mListener = listener;
        return instance;
    }

    public SliderMenuFragment() {

    }

    public void changeListener(ISldierMenuSelectionListener listener) {
        mListener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        List<AnimationGroups> headers = Arrays.asList(AnimationGroups.values());
        HashMap<AnimationGroups, List<Animations>> items = new HashMap<AnimationGroups, List<Animations>>();
        List<Animations> simpleAnimations = Arrays.asList(AnimationGroups.SIMPLE_ANIMATIONS.getAnimations());
        items.put(headers.get(0), simpleAnimations);

        View fView = inflater.inflate(R.layout.slider_menu_fragment, container, false);
        lvDrawerMenu = (ExpandableListView) fView.findViewById(R.id.lv_drawer_menu);
        mAdapter = new SliderMenuAdapter(getActivity(), headers, items);
        lvDrawerMenu.setAdapter(mAdapter);
        lvDrawerMenu.setOnChildClickListener(new OnChildClickListener() {


            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Animations child = (Animations) mAdapter.getChild(groupPosition, childPosition);
                mListener.onAimationSelected(child);
                return true;
            }
        });

        return fView;
    }
}
