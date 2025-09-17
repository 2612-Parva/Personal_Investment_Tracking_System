package com.investrack.app.api;

public class MutualFund {

    private int id;
    private String name;
    private String fundhouse;
    private float nav;

    public MutualFund() {
    }

    public MutualFund(int id, String name, String fundhouse, float nav) {
        this.id = id;
        this.name = name;
        this.fundhouse = fundhouse;
        this.nav = nav;
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

    public String getFundhouse() {
        return fundhouse;
    }

    public void setFundhouse(String fundhouse) {
        this.fundhouse = fundhouse;
    }

    public float getNav() {
        return nav;
    }

    public void setNav(float nav) {
        this.nav = nav;
    }
}
