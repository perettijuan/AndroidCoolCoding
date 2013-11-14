package com.jpp.animations;

public enum AnimationGroups {

    SIMPLE_ANIMATIONS("Simple Animations", new Animations[] { Animations.SIMPLE_SCALE }),
    CUSTOM_ANIMATIONS("Custom Animations", new Animations[] { Animations.LINEAR_INTERPOLATOR }),
    ANIMATION_TYPES("Animation Types", new Animations[] { Animations.ALPHA, Animations.ROTATE, Animations.SCALE, Animations.TRANSLATE });


    private String mName;
    private Animations[] mAnimations;

    private AnimationGroups(String name, Animations[] animations) {
        mName = name;
        mAnimations = animations;
    }

    public String getName() {
        return mName;
    }

    public Animations[] getAnimations() {
        return mAnimations;
    }
}
