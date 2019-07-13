package com.example.wlh.alarmtest;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final String INTENT_ALARM_LOG = "intent_alarm_log";

    private TimePicker timePicker;
    private CheckBox checkBox;
    private Button btnConfirm;

    private int hour;
    private int minute;

    AlarmManager am;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {
        timePicker = (TimePicker) findViewById(R.id.time_picker);
        timePicker.setIs24HourView(true);
        checkBox = (CheckBox) findViewById(R.id.checkbox);
        btnConfirm = (Button) findViewById(R.id.confirm);

        am = (AlarmManager) getSystemService(ALARM_SERVICE);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Build.VERSION.SDK_INT >= 23) {
                    hour = timePicker.getHour();
                    minute = timePicker.getMinute();
                } else {
                    hour = timePicker.getCurrentHour();
                    minute = timePicker.getCurrentMinute();
                }
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);

                Intent intent = new Intent(INTENT_ALARM_LOG);
                intent.setPackage(getPackageName());
                PendingIntent pi = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

                if (!checkBox.isChecked()) {
                    am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
                } else {
                    long intervalMillis  = 60 * 1000; // 60秒
                    am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), intervalMillis, pi);
                }
            }
        });
    }
}