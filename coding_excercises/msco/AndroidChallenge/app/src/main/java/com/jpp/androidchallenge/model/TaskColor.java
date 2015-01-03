package com.jpp.androidchallenge.model;

import com.jpp.androidchallenge.R;

/**
 * Enumeration used to show the color selection view to the user and
 * determinate which color is shown in each task
 */
public enum TaskColor {


    /*RED; ORANGE, GREEN BLUE, purlple*/

    NONE(0, 0, 0, 0, R.string.color_none),
    RED(1, R.drawable.red_circle, R.color.red_background, R.color.red_text, R.string.color_red),
    ORANGE(2, R.drawable.orange_circle, R.color.orange_background, R.color.orange_text, R.string.color_orange),
    GREEN(3, R.drawable.green_circle, R.color.green_background, R.color.green_text, R.string.color_green),
    BLUE(4, R.drawable.blue_circle, R.color.blue_background, R.color.blue_text, R.string.color_blue),
    PURPLE(5, R.drawable.purple_circle, R.color.purple_background, R.color.purple_text, R.string.color_purple);


    private int mIdentifier;
    private int mCircleSample;
    private int mBackgroundColor;
    private int mTextColor;
    private int mTextIdentifier;


    private TaskColor(int id, int sample, int backgroundColor, int textColor, int textIdentifier) {
        mIdentifier = id;
        mCircleSample = sample;
        mBackgroundColor = backgroundColor;
        mTextColor = textColor;
        mTextIdentifier = textIdentifier;
    }


    public int getSample() {
        return mCircleSample;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public int getTextIdentifier() {
        return mTextIdentifier;
    }

    public int getIdentifier() {
        return mIdentifier;
    }
}
