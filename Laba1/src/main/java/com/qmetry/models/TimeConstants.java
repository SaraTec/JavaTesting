package com.qmetry.models;

public enum TimeConstants {
    secondsInMinute(60),
    minutesInHour(60),
    secondsInHour(3600);

    public final int value;
    TimeConstants(int value){
        this.value = value;
    }
}
