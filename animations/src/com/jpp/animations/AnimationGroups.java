package com.jpp.animations;

public enum AnimationGroups {

    SIMPLE_ANIMATIONS("Simple Animations", new Animations[] { Animations.SIMPLE_SCALE }),
    CUSTOM_ANIMATIONS("Custom Animations (Interpolators)", new Animations[] { Animations.LINEAR_INTERPOLATOR, Animations.ACCELERATE_INTERPOLATOR,
            Animations.DECELERATE_INTERPOLATOR, Animations.ACCELERATE_DECELERATE_INTERPOLATOR, Animations.ANTICIPATE_INTERPOLATOR,
            Animations.OVERSHOOT_INTERPOLATOR, Animations.ANTICIPATE_OVERSHOOT_INTERPOLATOR }),
    ANIMATION_TYPES("Animation Types", new Animations[] { Animations.ALPHA, Animations.ROTATE, Animations.SCALE, Animations.TRANSLATE,
            Animations.COMBINATED_ANIMATIONS, Animations.SEQUENCE_ANIMATIONS });


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
