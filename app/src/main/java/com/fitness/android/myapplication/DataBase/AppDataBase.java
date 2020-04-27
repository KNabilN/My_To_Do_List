package com.fitness.android.myapplication.DataBase;

import android.content.Context;
import android.util.Log;

import com.fitness.android.myapplication.POJO.Tasks;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = Tasks.class, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase
{
    private static AppDataBase INSTANCE;
    private static final String NAME = "tasks";
    private static final Object LOCK = new Object();

    public static AppDataBase getInstance(Context context)
    {
        if (INSTANCE == null){
            synchronized (LOCK)
            {
                Log.d("KOKO", "Creating new database instance");

                INSTANCE = Room.databaseBuilder(context, AppDataBase.class
                , NAME)
                        .allowMainThreadQueries()
                .build();
            }

        }
        return INSTANCE;
    }

    public abstract TaskDao doActoin();
}
