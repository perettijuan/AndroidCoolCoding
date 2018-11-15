package com.jpp.androidchallenge.provider;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * Wrapper around the OS shared preferences framework.
 */
public class StorageManager {


    private static final String PREFERENCES_NAME = "AndroidChallenge";


    /**
     * Get a string from the local device preferences.
     *
     * @param context - the application's {@link Context}
     * @param key     - the preference key to look for.
     * @return the String stored in the preferences, null if no one is found.
     */
    public static String getString(Context context, String key) {
        return getPreferenceInstance(context).getString(key, null);
    }

    /**
     * Puts a new string value in the local device preferences.
     *
     * @param context - the application's {@link Context}
     * @param key     - the key of the string to store
     * @param value   - the value to store.
     */
    public static void putString(Context context, String key, String value) {
        Editor editor = getPreferencesEditor(context);
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * Remove a key from the local device preferences.
     *
     * @param context App context.
     * @param key     The key to remove.
     */
    public static void removeKey(Context context, String key) {
        Editor editor = getPreferencesEditor(context);
        editor.remove(key);
        editor.commit();
    }

    /**
     * Get a long value from the local device preferences.
     *
     * @param context - the application's {@link Context}
     * @param key     - the preference key to look for.
     * @return the long stored in the preferences, -1 if no one is found.
     */
    public static long getLong(Context context, String key) {
        return getPreferenceInstance(context).getLong(key, -1);
    }

    /**
     * Puts a new long value in the local device preferences.
     *
     * @param context - the application's {@link Context}
     * @param key     - the key of the long to store
     * @param value   - the value to store.
     */
    public static void putLong(Context context, String key, long value) {
        Editor editor = getPreferencesEditor(context);
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * Get an integer value from the local device preferences.
     *
     * @param context - the application's {@link Context}
     * @param key     - the preference key to look for.
     * @return the integer stored in the preferences, -1 if no one is found.
     */
    public static int getInt(Context context, String key) {
        return getPreferenceInstance(context).getInt(key, -1);
    }

    /**
     * Puts a new integer value in the local device preferences.
     *
     * @param context - the application's {@link Context}
     * @param key     - the key of the integer to store
     * @param value   - the value to store.
     */
    public static void putInt(Context context, String key, int value) {
        Editor editor = getPreferencesEditor(context);
        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * Get a boolean value from the local device preferences.
     *
     * @param context - the application's {@link Context}
     * @param key     - the preference key to look for.
     * @return the boolean value stored in the preferences, false if no one is
     * found.
     */
    public static boolean getBoolean(Context context, String key) {
        return getPreferenceInstance(context).getBoolean(key, false);
    }

    /**
     * Puts a new boolean value in the local device preferences.
     *
     * @param context - the application's {@link Context}
     * @param key     - the key of the boolean to store
     * @param value   - the value to store.
     */
    public static void putBoolean(Context context, String key, boolean value) {
        Editor editor = getPreferencesEditor(context);
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * Get a float value from the local device preferences.
     *
     * @param context - the application's {@link Context}
     * @param key     - the preference key to look for.
     * @return the float stored in the preferences, -1 if no one is found.
     */
    public static float getFloat(Context context, String key) {
        return getPreferenceInstance(context).getFloat(key, -1);
    }

    /**
     * Puts a new float value in the local device preferences.
     *
     * @param context - the application's {@link Context}
     * @param key     - the key of the float to store
     * @param value   - the value to store.
     */
    public static void putFloat(Context context, String key, float value) {
        Editor editor = getPreferencesEditor(context);
        editor.putFloat(key, value);
        editor.commit();
    }

    /**
     * Retrieve the {@link SharedPreferences} named with
     * {@link #PREFERENCES_NAME}.
     */
    private static SharedPreferences getPreferenceInstance(Context context) {
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    /**
     * Retrieve a {@link Editor} from the preferences used by the application.
     */
    private static SharedPreferences.Editor getPreferencesEditor(Context context) {
        return getPreferenceInstance(context).edit();
    }


}
