package com.kowalma.firetv.sleeptimer;

/**
 * Created by kowalma on 09.12.17.
 */

public class TimerCard {
    private String title;
    private int timeInMin;

    public TimerCard(String title, int timeInMin) {
        this.title = title;
        this.timeInMin = timeInMin;
    }

    public String getTitle() {
        return this.title;
    }

    public int getTimeInMin() {
        return timeInMin;
    }
}