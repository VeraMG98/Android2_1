package com.example.android2_1;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    private SharedPreferences preferences;

    public Prefs(Context context) {
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public  Boolean isShown() {
        return preferences.getBoolean("isShown", false);
    }

    public void saveBoardStatus() {
         preferences.edit().putBoolean("isShow", true).apply();
    }

    public void deleteStatus() {
        preferences.edit().remove("isShow").apply();
    }
}
