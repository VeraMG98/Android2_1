package com.example.android2_1;

import android.app.Application;

import androidx.room.Room;

import com.example.android2_1.room.AppDataBase;

public class App extends Application {

    private static AppDataBase appDataBase;

    @Override
    public void onCreate() {
        super.onCreate();
        appDataBase = Room.databaseBuilder(
                this,
                AppDataBase.class,
                "database")
                .allowMainThreadQueries()
                .build();
    }

    public static AppDataBase getAppDataBase() {
        return appDataBase;
    }
}
