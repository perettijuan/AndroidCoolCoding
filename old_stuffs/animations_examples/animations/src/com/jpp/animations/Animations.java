package com.jpp.animations;

public enum Animations {

    SIMPLE_SCALE("Simple scale"),
    LINEAR_INTERPOLATOR("Linear Interpolator", R.anim.linear_interpolator),
    SCALE("Scale", R.anim.scale),
    ALPHA("Alpha", R.anim.alpha),
    ROTATE("Rotate", R.anim.rotate),
    TRANSLATE("Translate", R.anim.translate),
    COMBINATED_ANIMATIONS("Combinated animations", R.anim.combinated_animations), 
    SEQUENCE_ANIMATIONS("Sequence of animations" , R.anim.sequence_animations),
    ACCELERATE_INTERPOLATOR("Accelerate interpolator" , R.anim.accelerate_interpolator),
    DECELERATE_INTERPOLATOR("Decelerate Interpolator", R.anim.decelerate_interpolator),
    ACCELERATE_DECELERATE_INTERPOLATOR("Accelerate decelerate interpolator" , R.anim.acceletare_decelerate_interpolator),
    ANTICIPATE_INTERPOLATOR("Anticipate interpolator" , R.anim.anticipate_interpolator),
    OVERSHOOT_INTERPOLATOR("Overshoot interpolator" , R.anim.overshoot_interpolator),
    ANTICIPATE_OVERSHOOT_INTERPOLATOR("Anticipate Overshoot interpolator" , R.anim.anticipate_overshoot_interpolator),
    PROGRAMMED_TRANSLATE("Programmed Translate"),
    PROGRAMMED_ANIMATION_SET("Programmed Animation Set");


    private String mName;
    private int mAnimationId;

    private Animations(String name) {
        mName = name;
    }

    private Animations(String name, int animId) {
        mName = name;
        mAnimationId = animId;
    }

    public String getName() {
        return mName;
    }

    public int getAnimId() {
        return mAnimationId;
    }
}
