package com.investrack.app.db;

import androidx.room.Embedded;

public class InvestmentData {

    @Embedded
    private Investment investment;

    @Embedded
    private Scheme scheme;

    public InvestmentData() {
    }

    public InvestmentData(Investment investment, Scheme scheme) {
        this.investment = investment;
        this.scheme = scheme;
    }

    public Investment getInvestment() {
        return investment;
    }

    public void setInvestment(Investment investment) {
        this.investment = investment;
    }

    public Scheme getScheme() {
        return scheme;
    }

    public void setScheme(Scheme scheme) {
        this.scheme = scheme;
    }
}
