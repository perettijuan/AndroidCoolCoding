package com.jpp.androidchallenge.util;

import android.os.Build;

/**
 * Created by jperett on 04/01/2015.
 */
public class Utils {


    public static boolean isRunningOnIceCreamSandwitch() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH;
    }
}
