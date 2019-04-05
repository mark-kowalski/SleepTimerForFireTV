/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package com.kowalma.firetv.sleeptimer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/*
 * MainActivity class that loads {@link MainFragment}.
 */
public class MainActivity extends Activity {
    private AlarmManagerService alarmManagerService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmManagerService = new AlarmManagerService();
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkForRunningTimer();
    }

    public void checkForRunningTimer() {
        String alarmString = alarmManagerService.getSetAlarmString(this);

        View cancelButton = findViewById(R.id.cancel_timer);
        View mainFragment = findViewById(R.id.main_fragment);
        TextView infoText = findViewById(R.id.info_text);

        if (alarmString != null) {
            mainFragment.setVisibility(View.GONE);
            cancelButton.setVisibility(View.VISIBLE);
            infoText.setText(getString(R.string.timer_set) + " " + alarmString);
            setClickListenerForCancelButton(cancelButton);
        } else {
            mainFragment.setVisibility(View.VISIBLE);
            cancelButton.setVisibility(View.GONE);
            infoText.setText(R.string.info);
        }
    }

    private void setClickListenerForCancelButton(View cancelButton) {
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alarmManagerService.cancelAlarm(MainActivity.this);
                checkForRunningTimer();
            }
        });
    }
}