package com.example.admin.myapplication_test;

import android.app.Activity;
import android.content.SharedPreferences;

public class temperature {
    SharedPreferences prefs;

    public temperature(Activity activity){
        prefs = activity.getPreferences(Activity.MODE_PRIVATE);
    }

    public double getNhietdo() {
        return prefs.getFloat("nhietdo", 0);
    }

    public void setNhietdo(float nhietdo) {
        prefs.edit().putFloat("nhietdo", nhietdo).commit();
    }
}
