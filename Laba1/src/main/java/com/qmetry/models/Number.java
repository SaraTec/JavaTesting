package com.qmetry.models;

public class Number {
    private String value;
    private NumbericSystem numbericSystem;

    public Number(String value, NumbericSystem numbericSystem){
        this.value = value;
        this.numbericSystem = numbericSystem;
    }

    public String getValue() {
        return value;
    }
    public NumbericSystem getSystem() {
        return numbericSystem;
    }
}
