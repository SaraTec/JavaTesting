package com.qmetry;

import org.apache.log4j.Logger;

public class TimeCalculator {
    final static Logger logger = Logger.getLogger(TimeCalculator.class);

    public Time sum(Time t1, Time t2) throws Exception{
        logger.info("sum method");

        return new Time(t1.getHours() + t2.getHours(), t1.getMinutes() + t2.getMinutes(), t1.getSeconds() + t2.getSeconds());
    }
    public Time difference(Time t1, Time t2) throws Exception{
        logger.info("difference method");

        Time time = new Time();
        time.setSeconds(Math.abs(t1.getAllSeconds() - t2.getAllSeconds()));
        return  time;
    }
    public boolean equal(Time t1, Time t2) {
        logger.info("equal method");

        return (t1.getHours() == t2.getHours() &&  t1.getMinutes() == t2.getMinutes() && t1.getSeconds() == t2.getSeconds());
    }
}
