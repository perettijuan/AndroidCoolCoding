package com.jpp.animations.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.jpp.animations.Animations;
import com.jpp.animations.R;

public class ProgrammedSetAnimationFragment extends Fragment implements OnClickListener {


    private ImageView ivPickachu;

    public static ProgrammedSetAnimationFragment newInstance(Animations animation) {
        ProgrammedSetAnimationFragment instance = new ProgrammedSetAnimationFragment();
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
        /*
         * view that spins onto the screen, just like a newspaper spin s into
         * view in the old movies. Combine: a rotate animation, a scale
         * animation and an alpha animation as well
         */

        /*
         * The rotation should be around the view’s centre, so we need to
         * specify the pivot relative to the view’s dimensions
         */
        RotateAnimation rotate = new RotateAnimation(0.0f, 1080.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        /*
         * scale animation. This should simply scale the animation from 0 to
         * 100%.
         */
        ScaleAnimation scale = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        /*
         * the alpha animation. This is as simple as the scale animation. We
         * want to change the alpha value from 0 to 1.
         */
        AlphaAnimation alpha = new AlphaAnimation(0.0f, 1.0f);

        /*
         * We need an animation set to put it all together. For this we create
         * an AnimationSet object and add all our animations to it.
         */
        AnimationSet set = new AnimationSet(true);
        set.addAnimation(rotate);
        set.addAnimation(scale);
        set.addAnimation(alpha);
        set.setDuration(2000);

        ivPickachu.startAnimation(set);
    }

}
