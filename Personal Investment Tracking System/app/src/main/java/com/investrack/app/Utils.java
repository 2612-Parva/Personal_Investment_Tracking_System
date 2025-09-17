package com.investrack.app;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.format.DateUtils;

import androidx.browser.customtabs.CustomTabsIntent;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Utils {

    static void launchURL(Context context, String url) {
        CustomTabsIntent.Builder builderCustomTabs = new CustomTabsIntent.Builder();
        CustomTabsIntent intentCustomTabs = builderCustomTabs.build();
        intentCustomTabs.intent.setPackage("com.android.chrome");
        intentCustomTabs.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intentCustomTabs.launchUrl(context, Uri.parse(url));
    }

    static String formatTime(String time) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        try {
            return DateUtils.getRelativeTimeSpanString(df.parse(time).getTime()).toString();
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }
}
