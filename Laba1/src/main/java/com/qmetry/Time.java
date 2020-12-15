package com.qmetry;

import com.qmetry.models.TimeConstants;
import com.qmetry.models.TimeModel;
import org.apache.log4j.Logger;

public class Time {
    final static Logger logger = Logger.getLogger(Time.class);
    private TimeModel timeModel;

    public Time(){
        timeModel = new TimeModel();
    }
    public Time(int hours, int minutes, int seconds)throws Exception {
        timeModel = new TimeModel();

        if (hours < 0 || minutes < 0 || seconds < 0 ) {
            throw new Exception("hour || minutes || seconds can't be less than 0");
        }

        this.setSeconds(hours * TimeConstants.secondsInHour.value + minutes * TimeConstants.secondsInMinute.value + seconds);
    }
    public void setSeconds(int seconds) throws Exception {
        logger.info("setSeconds method");
        if (seconds < 0) {
            throw new Exception("");
        }

        long longVal = seconds;
        timeModel.hours = (int) longVal / TimeConstants.secondsInHour.value;
        int remainder = (int) longVal - timeModel.hours * TimeConstants.secondsInHour.value;
        timeModel.minutes = remainder / TimeConstants.secondsInMinute.value;
        remainder = remainder - timeModel.minutes * TimeConstants.secondsInMinute.value;
        timeModel.seconds = remainder;
    }
    public void setMinutes(int minutes) throws Exception {
        logger.info("setMinutes method");

        if (minutes < 0) {
            throw new Exception("minutes can't be less than 0");
        }

        long longVal = minutes;
        timeModel.hours = (int) longVal / TimeConstants.minutesInHour.value;
        int remainder = (int) longVal - timeModel.hours * TimeConstants.minutesInHour.value;
        timeModel.minutes = remainder;
    }
    public void setHours(int hours) throws Exception {
        logger.info("setHours method");

        if (hours < 0) {
            throw new Exception("hours can't be less than 0");
        }
        timeModel.hours = hours;
    }
    public int getSeconds(){
        logger.info("getSeconds method");
        return timeModel.seconds;
    }
    public int getMinutes(){
        logger.info("getMinutes method");
        return timeModel.minutes;
    }
    public int getHours(){
        logger.info("getHours method");
        return timeModel.hours;
    }
    public int getAllSeconds(){
        logger.info("getAllSeconds method");
        return timeModel.seconds + timeModel.minutes * TimeConstants.secondsInMinute.value + timeModel.hours * TimeConstants.secondsInHour.value;
    }
    public double getAllMinutes(){
        logger.info("getAllMinutes method");
        return (double)timeModel.seconds/TimeConstants.secondsInMinute.value + timeModel.minutes + timeModel.hours * TimeConstants.minutesInHour.value;
    }
    public double getAllHours(){
        logger.info("getAllHours method");
        return (double) timeModel.seconds/TimeConstants.secondsInHour.value + (double)timeModel.minutes/TimeConstants.secondsInMinute.value + timeModel.hours;
    }
}
