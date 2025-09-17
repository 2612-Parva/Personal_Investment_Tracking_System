package com.investrack.app.api;

public class Stock {

    private int id;
    private String name;
    private String market;
    private float sharePrice;

    public Stock() {

    }

    public Stock(int id, String name, String market, float sharePrice) {
        this.id = id;
        this.name = name;
        this.market = market;
        this.sharePrice = sharePrice;
    }

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


    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public float getSharePrice() {
        return sharePrice;
    }

    public void setSharePrice(float sharePrice) {
        this.sharePrice = sharePrice;
    }
}
