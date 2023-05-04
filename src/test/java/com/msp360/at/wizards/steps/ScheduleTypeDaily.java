package com.msp360.at.wizards.steps;

public enum ScheduleTypeDaily {

    TYPE_DAILY("Daily"),

    TYPE_DAILY_MONDAY("Mon"),
    TYPE_DAILY_TUESDAY("Tue"),
    TYPE_DAILY_WEDNESDAY("Wed"),
    TYPE_DAILY_THURSDAY("Thu"),
    TYPE_DAILY_FRIDAY("Fri"),
    TYPE_DAILY_SATURDAY("Sat"),
    TYPE_DAILY_SUNDAY("Sun");


    private final String type;

    ScheduleTypeDaily(String classType) {
        this.type = classType;
    }

    public String toString() {
        return type;

    }

}



