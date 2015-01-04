package com.jpp.androidchallenge.ui.extention;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A TextView implementation used to show a specific type of style (Roboto).
 */
@SuppressLint("NewApi")
public class RobotoTextView extends TextView {


    private static final String PATH_BOLD = "fonts/RobotoCondensed-Bold.ttf";
    private static final String PATH_REGULAR = "fonts/RobotoCondensed-Regular.ttf";

    public RobotoTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public RobotoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public RobotoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RobotoTextView(Context context) {
        super(context);
    }

    @Override
    public void setTypeface(Typeface tf, int style) {
        String path = PATH_REGULAR;
        if (style == Typeface.BOLD) {
            path = PATH_BOLD;
        }
        Typeface roboto = Typeface.createFromAsset(getContext().getAssets(), path);
        super.setTypeface(roboto);
    }

    public void remove() {
        ViewGroup parent = (ViewGroup) getParent();
        if (parent != null) {
            parent.removeView(this);
        }
    }
}
