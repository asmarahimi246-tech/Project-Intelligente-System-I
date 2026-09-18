package org.example;

import jdk.swing.interop.SwingInterOpUtils;

public class Time {
    static void main(String[] args) {
        int hour = 14;
        int minute = 30;
        int second =  45;

        int secondSinceMidnight = second * 3600 + minute * 60 + second;
        System.out.println(secondSinceMidnight);

        int secondsPerDay = 24 * 60 * 60;
        int secondremaining = secondsPerDay -  secondSinceMidnight;
        System.out.println(secondremaining);

        double percentPassed = (secondSinceMidnight * 100) / secondsPerDay;
        System.out.println(percentPassed);

        int startHour = 14;
        int startMinute = 20;
        int startSecond = 0;

        int startsSecond = startHour * 3600 + startMinute * 60 + startSecond;

        int elapsedTime = secondSinceMidnight - startSecond;
        System.out.println(elapsedTime);
    }
}
