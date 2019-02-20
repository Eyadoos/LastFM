package com.appsfactory.lastfm.utils;

import android.app.Activity;
import android.view.inputmethod.InputMethodManager;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

/**
 * Created by Mhanna, Eyad on 17/02/2019.
 */

public class AppUtils {

    public static Date getCurrentDateTime() {
        return Calendar.getInstance().getTime();
    }

    public static String convertSecondstoMinutes(String seconds) {

        // Convert to Milliseconds
        int millis = Integer.parseInt(seconds) * 1000;

        // Convert to human readable duration and return the value
        return String.format(Locale.US, "%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(millis),
                TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));

    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

}
