package com.example.myapplication.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.myapplication.data.databaseUtils.Converters;
import com.example.myapplication.data.model.ConsumerDao;
import com.example.myapplication.data.model.entities.Consumer;

@Database(entities = {Consumer.class}, version = 7)
@TypeConverters({Converters.class})
public abstract class ConsumerDatabase extends RoomDatabase {

    private static volatile ConsumerDatabase INSTANCE;

    public static ConsumerDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (ConsumerDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ConsumerDatabase.class, "consumer_database")
                            // .fallbackToDestructiveMigration() // Descomente se quiser permitir migrações destrutivas
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    // Método abstrato para obter o DAO
    public abstract ConsumerDao consumerDao();
}