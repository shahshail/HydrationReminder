package com.geekathlon.shailshah.hydrationreminder.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by shailshah on 10/8/17.
 * This class will help us to provide water count and other user functionality..
 *
 */

public final class PreferenceUtils {

    public static final String KEY_WATER_COUNT = "water-count";
    public static final String KEY_CHARGING_REMINDER_COUNT = "charging-reminder-count";

    private static final int DEFAULT_COUNT = 0;

    synchronized private static void setWaterCount(Context context, int glassofwater)
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(KEY_WATER_COUNT,glassofwater);
        editor.apply();
    }

    public static int getWaterCount (Context context)
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        int glassofwater = pref.getInt(KEY_WATER_COUNT,DEFAULT_COUNT);
        return glassofwater;
    }

    synchronized public static void incrementWeatherCount (Context context)
    {
        int watercount = PreferenceUtils.getWaterCount(context);
        PreferenceUtils.setWaterCount(context,++watercount);
    }

    synchronized  public static void incrementChargingReminderCount (Context context)
    {
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
        int chargingReminder = pref.getInt(KEY_CHARGING_REMINDER_COUNT,DEFAULT_COUNT);

        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(KEY_CHARGING_REMINDER_COUNT,chargingReminder++);
        editor.apply();
    }

    public static int getChargingReminderCount(Context context) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        int chargingReminders = prefs.getInt(KEY_CHARGING_REMINDER_COUNT, DEFAULT_COUNT);
        return chargingReminders;
    }



}
