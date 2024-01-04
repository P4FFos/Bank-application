package com.piggybank.app.backend.utils;

public class TruncationUtil {
    public static double truncate(double value) { // truncates double value to 2 decimal places
        return Math.floor(value * 100) / 100;
    }
}
