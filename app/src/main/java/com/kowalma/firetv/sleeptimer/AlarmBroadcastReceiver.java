package com.kowalma.firetv.sleeptimer;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by kowalma on 09.12.17.
 */

public class AlarmBroadcastReceiver extends BroadcastReceiver {
    public static final String ACTION_ALARM = "kowalma.sleeptimer.ACTION_ALARM";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (ACTION_ALARM.equals(intent.getAction())) {
            Toast.makeText(context, "Timer arrived", Toast.LENGTH_LONG).show();

            AlarmManagerService alarmManagerService = new AlarmManagerService();
            alarmManagerService.setPreferences(context, false, null);

            Intent intentToStart = new Intent();
            intentToStart.setClassName("com.kowalma.firetv.sleeptimer", "com.kowalma.firetv.sleeptimer.MainActivity");
            intentToStart.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intentToStart);
        }
    }
}