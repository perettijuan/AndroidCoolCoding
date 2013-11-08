package com.jpp.animations;

public enum Animations {

    SIMPLAE_SCALE("Simple scale");


    private String mName;

    private Animations(String name) {
        mName = name;
    }

    public String getName() {
        return mName;
    }
}
