package com.investrack.app.api;

public class Bank {

    private int id;
    private String name;
    private String address;
    private String branch;
    private float rateOfInterest;


    public Bank(int id, String name, String address, String branch, float rateOfInterest) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.branch = branch;
        this.rateOfInterest = rateOfInterest;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public float getRateOfInterest() {
        return rateOfInterest;
    }

    public void setRateOfInterest(float rateOfInterest) {
        this.rateOfInterest = rateOfInterest;
    }
}
