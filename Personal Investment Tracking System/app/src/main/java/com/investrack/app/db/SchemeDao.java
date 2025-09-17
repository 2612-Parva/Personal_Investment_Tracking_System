package com.investrack.app.db;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SchemeDao {

    @Query("SELECT * from scheme_table ORDER BY name ASC")
    List<Scheme> getAllSchemes();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Scheme scheme);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<Scheme> schemes);

    @Query("DELETE FROM scheme_table")
    void clearAll();
}
