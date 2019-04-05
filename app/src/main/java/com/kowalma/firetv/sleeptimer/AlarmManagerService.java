package com.kowalma.firetv.sleeptimer;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by kowalma on 09.12.17.
 */

public class AlarmManagerService {
    private String APPIDENTIFIER = "com.kowalma.firetv.sleeptimer";
    private String HAS_TIMER_KEY = "HAS_TIMER";
    private String TIMER_UNTIL_KEY = "TIMER_UNTIL";

    public void setAlarm(Context context, int timeUntilAlarmInMin) {
        Toast.makeText(context, "Timer set", Toast.LENGTH_LONG).show();

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MINUTE, timeUntilAlarmInMin);
        long timeUntil = calendar.getTimeInMillis();

        Context applicationContext = context.getApplicationContext();
        PendingIntent alarmIntent = getAlarmIntent(applicationContext);

        AlarmManager alarmManager = (AlarmManager)applicationContext.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC, timeUntil, alarmIntent);

        setPreferences(applicationContext, true, calendar);
    }

    public String getSetAlarmString(Context context) {
        Context applicationContext = context.getApplicationContext();

        boolean hasTimer = hasTimerSet(applicationContext);

        if (hasTimer) {
            return getTimerUntilTime(applicationContext);
        }

        return null;
    }

    public void cancelAlarm(Context context) {
        Context applicationContext = context.getApplicationContext();
        PendingIntent alarmIntent = getAlarmIntent(applicationContext);

        AlarmManager alarmManager = (AlarmManager) applicationContext.getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(alarmIntent);

        setPreferences(applicationContext, false, null);
    }

    private PendingIntent getAlarmIntent(Context applicationContext) {
        Intent intentToFire = new Intent(applicationContext, AlarmBroadcastReceiver.class);
        intentToFire.setAction(AlarmBroadcastReceiver.ACTION_ALARM);
        return PendingIntent.getBroadcast(applicationContext, 0, intentToFire, 0);
    }

    public void setPreferences(Context context, boolean hasTimer, Calendar calendar) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APPIDENTIFIER, Context.MODE_PRIVATE);
        String timerUntilString = "";

        if (calendar != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            timerUntilString = simpleDateFormat.format(calendar.getTime());
        }

        sharedPreferences.edit().putBoolean(HAS_TIMER_KEY, hasTimer).commit();
        sharedPreferences.edit().putString(TIMER_UNTIL_KEY, timerUntilString).commit();
    }

    private boolean hasTimerSet(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APPIDENTIFIER, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(HAS_TIMER_KEY, false);
    }

    private String getTimerUntilTime(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(APPIDENTIFIER, Context.MODE_PRIVATE);
        return sharedPreferences.getString(TIMER_UNTIL_KEY, "");
    }
}