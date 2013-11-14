package com.jpp.animations;

public enum Animations {

    SIMPLE_SCALE("Simple scale"),
    LINEAR_INTERPOLATOR("Linear Interpolator", R.anim.linear_interpolator),
    SCALE("Scale", R.anim.scale),
    ALPHA("Alpha", R.anim.alpha),
    ROTATE("Rotate", R.anim.rotate),
    TRANSLATE("Translate", R.anim.translate);


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
