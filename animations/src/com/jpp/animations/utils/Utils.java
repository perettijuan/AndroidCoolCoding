package com.jpp.animations.utils;

import java.lang.reflect.Field;

import android.content.Context;
import android.view.ViewConfiguration;

public class Utils {


    /**
     * Forces to show the overflow menu always
     * 
     * @param context
     */
    public static void alwaysShowTheOverflowMenu(Context context) {
        try {
            ViewConfiguration config = ViewConfiguration.get(context);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
