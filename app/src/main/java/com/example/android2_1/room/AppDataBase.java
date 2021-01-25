package com.example.android2_1.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.android2_1.Models.Note;

@Database(entities = {Note.class}, version = 1)
public abstract class AppDataBase extends RoomDatabase {
    public  abstract NoteDao noteDao();
}
