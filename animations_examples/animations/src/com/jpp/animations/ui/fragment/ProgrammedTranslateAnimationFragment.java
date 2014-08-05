package com.jpp.animations.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.jpp.animations.Animations;
import com.jpp.animations.R;

public class ProgrammedTranslateAnimationFragment extends Fragment implements OnClickListener {


    private ImageView ivPickachu;

    public static ProgrammedTranslateAnimationFragment newInstance(Animations animation) {
        ProgrammedTranslateAnimationFragment instance = new ProgrammedTranslateAnimationFragment();
        return instance;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fView = inflater.inflate(R.layout.custom_animations_fragment, container, false);
        ivPickachu = (ImageView) fView.findViewById(R.id.iv_pickachu);
        ivPickachu.setOnClickListener(this);
        return fView;
    }

    @Override
    public void onClick(View v) {
        Animation animation = new TranslateAnimation(Animation.ABSOLUTE, -10, Animation.ABSOLUTE, -10, Animation.RELATIVE_TO_SELF, 0.5F,
                Animation.RELATIVE_TO_PARENT, 0.8F);
        ivPickachu.startAnimation(animation);
    }

}
