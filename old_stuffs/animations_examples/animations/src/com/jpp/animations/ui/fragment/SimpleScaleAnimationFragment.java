package com.jpp.animations.ui.fragment;

import com.jpp.animations.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SimpleScaleAnimationFragment extends Fragment implements OnClickListener {

    public static final String TAG = SimpleScaleAnimationFragment.class.getName();

    private ImageView imView1;
    private ImageView imView2;
    private ImageView animatedImage;

    public static SimpleScaleAnimationFragment newInstance() {
        return new SimpleScaleAnimationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fView = inflater.inflate(R.layout.simple_scale_animation_fragment, container, false);
        imView1 = (ImageView) fView.findViewById(R.id.imageView1);
        imView1.setOnClickListener(this);
        imView2 = (ImageView) fView.findViewById(R.id.imageView2);
        imView2.setOnClickListener(this);
        animatedImage = (ImageView) fView.findViewById(R.id.animatedImage);
        return fView;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == imView1.getId()) {
            animatedImage.setImageDrawable(imView1.getDrawable());
        } else if (v.getId() == imView2.getId()) {
            animatedImage.setImageDrawable(imView2.getDrawable());
        }
        animatedImage.setVisibility(View.VISIBLE);
        Animation animation = AnimationUtils.loadAnimation(getActivity(), R.anim.simple_scale_animation);
        animatedImage.startAnimation(animation);
    }

}
