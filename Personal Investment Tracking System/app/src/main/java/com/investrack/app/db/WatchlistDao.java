package com.investrack.app.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WatchlistDao {

    @Query("SELECT scheme_table.*, watchlist_table.date as date " +
            "FROM scheme_table INNER JOIN watchlist_table " +
            "ON scheme_table.id = watchlist_table.schemeId " +
            "ORDER BY watchlist_table.date DESC")
    List<WatchlistData> getWatchlist();

    @Insert
    void add(Watchlist watchlist);

    @Delete
    void delete(Watchlist watchlist);

}
