package com.jpp.animations.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.jpp.animations.Animations;
import com.jpp.animations.R;

public class AnimationTypesFragment extends Fragment implements OnClickListener {


    private Animations mAnimationSelected;
    private ImageView ivPickachu;

    public static AnimationTypesFragment newInstance(Animations animation) {
        AnimationTypesFragment instance = new AnimationTypesFragment();
        instance.mAnimationSelected = animation;
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fView = inflater.inflate(R.layout.animations_type, container, false);
        ivPickachu = (ImageView) fView.findViewById(R.id.iv_pickachu);
        ivPickachu.setOnClickListener(this);
        return fView;
    }

    @Override
    public void onClick(View v) {
        Animation animation = AnimationUtils.loadAnimation(getActivity(), mAnimationSelected.getAnimId());
        ivPickachu.startAnimation(animation);
    }
}
