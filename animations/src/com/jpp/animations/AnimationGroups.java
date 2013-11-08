package com.jpp.animations;

public enum AnimationGroups {

    SIMPLE_ANIMATIONS("Simple Animations", new Animations[] { Animations.SIMPLAE_SCALE });


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
