/*
 * Copyright (C) 2017 The Android Open Source Project
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

import android.os.Bundle;
import android.support.v17.leanback.app.VerticalGridFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v17.leanback.widget.VerticalGridPresenter;

public class MainFragment extends VerticalGridFragment implements OnItemViewClickedListener {
    private ArrayObjectAdapter objectAdapter;
    private AlarmManagerService alarmManagerService;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(getString(R.string.browse_title));
        alarmManagerService = new AlarmManagerService();
        setupFragment();
    }

    private void setupFragment() {
        VerticalGridPresenter gridPresenter = new VerticalGridPresenter();
        gridPresenter.setNumberOfColumns(4);
        setGridPresenter(gridPresenter);

        objectAdapter = new ArrayObjectAdapter(new CardPresenter());

        //objectAdapter.add(new TimerCard("1 min", 1));
        objectAdapter.add(new TimerCard("30 min", 30));
        objectAdapter.add(new TimerCard("1h", 60));
        objectAdapter.add(new TimerCard("1h 30 min", 90));
        objectAdapter.add(new TimerCard("2h", 120));
        objectAdapter.add(new TimerCard("2h 30 min", 150));
        objectAdapter.add(new TimerCard("3h", 180));
        setAdapter(objectAdapter);
        setOnItemViewClickedListener(this);
    }

    @Override
    public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
        if (item instanceof TimerCard) {
            TimerCard currentTimer = (TimerCard) item;
            alarmManagerService.setAlarm(this.getActivity(), currentTimer.getTimeInMin());
            ((MainActivity)getActivity()).checkForRunningTimer();
        }
    }
}