package com.example.myapplication.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.myapplication.data.databaseUtils.Converters;
import com.example.myapplication.data.model.ConsumerDao;
import com.example.myapplication.data.model.entities.Consumer;

@Database(entities = {Consumer.class}, version = 6, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract ConsumerDao consumerDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "app_database")
//                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}