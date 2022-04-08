package com.example.myapplication.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.myapplication.model.DogBreed;

@Database(entities = {DogBreed.class}, version = 1,exportSchema = true)
public abstract class DogDatabase extends RoomDatabase {
    public abstract RoomDao roomDao();

    private static DogDatabase instance;

    public static DogDatabase getInstance(Context context) {
        if(instance==null){
            instance = Room.databaseBuilder(context,
                    DogDatabase.class, "testmoi").allowMainThreadQueries().build();
        }
        return instance;
    }
}
