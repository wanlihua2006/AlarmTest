package com.example.wlh.alarmtest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (action == MainActivity.INTENT_ALARM_LOG) {
            Log.d("AlarmReceiver", "wanlihua debug log log log");
           // Toast.makeText(MainActivity.this, "AlarmReceiver is working!!", Toast.LENGTH_SHORT).show();
        }
    }
}
