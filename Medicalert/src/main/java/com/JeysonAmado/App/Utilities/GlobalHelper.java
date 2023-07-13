package com.JeysonAmado.App.Utilities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GlobalHelper {


    public static String formatDateTime(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm a");
        return dateTime.format(formatter);
    }
}
