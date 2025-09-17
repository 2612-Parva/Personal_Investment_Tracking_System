package com.investrack.app.db;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "scheme_table")
public class Scheme implements Parcelable {

    @PrimaryKey
    @NonNull
    private int id;
    private String name;
    private String type;
    private String bank;
    private String fundHouse;
    private String market;
    private float currentRate;

    public static String SCHEME_FD = "fd";
    public static String SCHEME_MF = "mf";
    public static String SCHEME_STOCK = "stock";

    public Scheme() {
    }

    public Scheme(int id, String name, String type, String bank, String fundHouse, String market, float currentRate) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.bank = bank;
        this.fundHouse = fundHouse;
        this.market = market;
        this.currentRate = currentRate;
    }

    protected Scheme(Parcel in) {
        id = in.readInt();
        name = in.readString();
        type = in.readString();
        bank = in.readString();
        fundHouse = in.readString();
        market = in.readString();
        currentRate = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(type);
        dest.writeString(bank);
        dest.writeString(fundHouse);
        dest.writeString(market);
        dest.writeFloat(currentRate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Scheme> CREATOR = new Creator<Scheme>() {
        @Override
        public Scheme createFromParcel(Parcel in) {
            return new Scheme(in);
        }

        @Override
        public Scheme[] newArray(int size) {
            return new Scheme[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getFundHouse() {
        return fundHouse;
    }

    public void setFundHouse(String fundHouse) {
        this.fundHouse = fundHouse;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public float getCurrentRate() {
        return currentRate;
    }

    public void setCurrentRate(float currentRate) {
        this.currentRate = currentRate;
    }
}
