package com.investrack.app.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Date;

@Entity(tableName = "transaction_table")
@TypeConverters(DateConverter.class)
public class Transaction {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int transId;
    private String type;
    private Date date;
    private float rate;
    private float amount;
    private int schemeId;

    public Transaction() {
    }

    public Transaction(String type, Date date, float units, float amount, int schemeId) {
        this.type = type;
        this.date = date;
        this.rate = units;
        this.amount = amount;
        this.schemeId = schemeId;
    }

    @NonNull
    public int getTransId() {
        return transId;
    }

    public void setTransId(@NonNull int transId) {
        this.transId = transId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(int schemeId) {
        this.schemeId = schemeId;
    }
}

