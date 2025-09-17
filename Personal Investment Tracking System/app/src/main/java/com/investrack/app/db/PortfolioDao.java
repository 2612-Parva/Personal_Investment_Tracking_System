package com.investrack.app.db;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PortfolioDao {

    @Query("SELECT * FROM scheme_table INNER JOIN investment_table " +
            "ON scheme_table.id = investment_table.schemeId")
    List<InvestmentData> getAllInvestments();

    @Query("SELECT * FROM scheme_table INNER JOIN investment_table " +
            "ON scheme_table.id = investment_table.schemeId " +
            "WHERE schemeId = :schemeId")
    Investment getInvestment(int schemeId);

    @Query("SELECT SUM(origValue) FROM investment_table")
    float getTotalInvestedAmount();

    @Query("SELECT SUM(currValue) FROM investment_table")
    float getTotalCurrentAmount();

    @Insert
    void add(Investment inv);

    @Update
    void update(Investment inv);

    @Delete
    void delete(Investment inv);

    @Insert
    void add(Transaction transaction);

    @Query("SELECT * from transaction_table WHERE schemeId = :schemeId ORDER BY date DESC")
    List<Transaction> getAllTransactions(int schemeId);

    @Query("DELETE from transaction_table WHERE schemeId = :schemeId")
    void deleteAllTransactions(int schemeId);
}
