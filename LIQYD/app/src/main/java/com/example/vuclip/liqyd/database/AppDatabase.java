package com.example.vuclip.liqyd.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.vuclip.liqyd.LiqydApp;
import com.example.vuclip.liqyd.models.Order;

/**
 * Created by Banty on 26/01/18.
 */

@Database(entities = {Order.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase INSTANCE;

    public abstract OrderDao orderDao();

    public static AppDatabase getAppDatebase() {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(LiqydApp.getAppInstance().getApplicationContext(), AppDatabase.class, "app-database")
                    .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

}
