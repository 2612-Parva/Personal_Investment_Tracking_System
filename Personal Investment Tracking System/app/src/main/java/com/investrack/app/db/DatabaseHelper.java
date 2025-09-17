package com.investrack.app.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Scheme.class, Investment.class, Transaction.class, Watchlist.class}, version = 1)
public abstract class DatabaseHelper extends RoomDatabase {

    private static volatile DatabaseHelper INSTANCE;

    public static DatabaseHelper getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DatabaseHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DatabaseHelper.class, "investrack_db")
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract SchemeDao schemeDao();

    public abstract PortfolioDao portfolioDao();

    public abstract WatchlistDao watchlistDao();
}
