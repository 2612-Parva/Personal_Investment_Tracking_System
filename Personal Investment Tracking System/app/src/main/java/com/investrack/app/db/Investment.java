package com.investrack.app.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "investment_table")
public class Investment {

    @PrimaryKey
    private int schemeId;
    private float currValue;
    private float origValue;

    public Investment() {
    }

    public Investment(int schemeId, float currValue, float origValue) {
        this.schemeId = schemeId;
        this.currValue = currValue;
        this.origValue = origValue;
    }

    public float getCurrValue() {
        return currValue;
    }

    public void setCurrValue(float currValue) {
        this.currValue = currValue;
    }

    public float getOrigValue() {
        return origValue;
    }

    public void setOrigValue(float origValue) {
        this.origValue = origValue;
    }

    public int getSchemeId() {
        return schemeId;
    }

    public void setSchemeId(int schemeId) {
        this.schemeId = schemeId;
    }
}
