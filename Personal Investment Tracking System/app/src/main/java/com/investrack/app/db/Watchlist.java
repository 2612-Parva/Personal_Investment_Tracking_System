package com.investrack.app.db;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "watchlist_table")
@TypeConverters(DateConverter.class)
public class Watchlist {

    @PrimaryKey
    @NonNull
    private int schemeId;
    private Date date;

    public Watchlist() {
    }

    public Watchlist(@NonNull int schemeId, Date date) {
        this.schemeId = schemeId;
        this.date = date;
    }

    @NonNull
    public int getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(@NonNull int schemeId) {
        this.schemeId = schemeId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

