package tech.jianka.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Richa on 2017/7/29.
 */

public class PreferenceHelper {
    private static String IDENTIFY = "tech.jianka";

    public static void putString(String pref,String key, String value, Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(pref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String getString(String pref,String key, Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(pref, Context.MODE_PRIVATE);
        return sharedPref.getString(key, "");
    }

    public static void putSet(String pref,String key, Set<String> values, Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(pref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putStringSet(key, values);
        editor.apply();
    }

    public static Set<String> getSet(String pref, String key,Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(pref, Context.MODE_PRIVATE);
        return sharedPref.getStringSet(key, new HashSet<String>());
    }

    public static void putInt( String pref,String key, int value,Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(pref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(String pref,String key, Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(pref, Context.MODE_PRIVATE);
        return sharedPref.getInt(key, 0);
    }

    public static void putBoolean(String pref,String key, boolean value, Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(pref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static boolean getBoolean(String pref, String key,Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(pref, Context.MODE_PRIVATE);
        return sharedPref.getBoolean(key, false);
    }
    public static void removeData(String key,String pref, Context context) {
        SharedPreferences sharedPref = context.getSharedPreferences(pref, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.remove(key);
        editor.apply();
    }

}
