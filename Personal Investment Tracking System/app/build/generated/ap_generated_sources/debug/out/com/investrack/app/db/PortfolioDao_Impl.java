package com.investrack.app.db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class PortfolioDao_Impl implements PortfolioDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Investment> __insertionAdapterOfInvestment;

  private final EntityInsertionAdapter<Transaction> __insertionAdapterOfTransaction;

  private final EntityDeletionOrUpdateAdapter<Investment> __deletionAdapterOfInvestment;

  private final EntityDeletionOrUpdateAdapter<Investment> __updateAdapterOfInvestment;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAllTransactions;

  public PortfolioDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfInvestment = new EntityInsertionAdapter<Investment>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `investment_table` (`schemeId`,`currValue`,`origValue`) VALUES (?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Investment value) {
        stmt.bindLong(1, value.getSchemeId());
        stmt.bindDouble(2, value.getCurrValue());
        stmt.bindDouble(3, value.getOrigValue());
      }
    };
    this.__insertionAdapterOfTransaction = new EntityInsertionAdapter<Transaction>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `transaction_table` (`transId`,`type`,`date`,`rate`,`amount`,`schemeId`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Transaction value) {
        stmt.bindLong(1, value.getTransId());
        if (value.getType() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getType());
        }
        final long _tmp = DateConverter.fromDate(value.getDate());
        stmt.bindLong(3, _tmp);
        stmt.bindDouble(4, value.getRate());
        stmt.bindDouble(5, value.getAmount());
        stmt.bindLong(6, value.getSchemeId());
      }
    };
    this.__deletionAdapterOfInvestment = new EntityDeletionOrUpdateAdapter<Investment>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `investment_table` WHERE `schemeId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Investment value) {
        stmt.bindLong(1, value.getSchemeId());
      }
    };
    this.__updateAdapterOfInvestment = new EntityDeletionOrUpdateAdapter<Investment>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `investment_table` SET `schemeId` = ?,`currValue` = ?,`origValue` = ? WHERE `schemeId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Investment value) {
        stmt.bindLong(1, value.getSchemeId());
        stmt.bindDouble(2, value.getCurrValue());
        stmt.bindDouble(3, value.getOrigValue());
        stmt.bindLong(4, value.getSchemeId());
      }
    };
    this.__preparedStmtOfDeleteAllTransactions = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE from transaction_table WHERE schemeId = ?";
        return _query;
      }
    };
  }

  @Override
  public void add(final Investment inv) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfInvestment.insert(inv);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void add(final Transaction transaction) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfTransaction.insert(transaction);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Investment inv) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfInvestment.handle(inv);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Investment inv) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfInvestment.handle(inv);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAllTransactions(final int schemeId) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAllTransactions.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, schemeId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAllTransactions.release(_stmt);
    }
  }

  @Override
  public List<InvestmentData> getAllInvestments() {
    final String _sql = "SELECT * FROM scheme_table INNER JOIN investment_table ON scheme_table.id = investment_table.schemeId";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfBank = CursorUtil.getColumnIndexOrThrow(_cursor, "bank");
      final int _cursorIndexOfFundHouse = CursorUtil.getColumnIndexOrThrow(_cursor, "fundHouse");
      final int _cursorIndexOfMarket = CursorUtil.getColumnIndexOrThrow(_cursor, "market");
      final int _cursorIndexOfCurrentRate = CursorUtil.getColumnIndexOrThrow(_cursor, "currentRate");
      final int _cursorIndexOfSchemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "schemeId");
      final int _cursorIndexOfCurrValue = CursorUtil.getColumnIndexOrThrow(_cursor, "currValue");
      final int _cursorIndexOfOrigValue = CursorUtil.getColumnIndexOrThrow(_cursor, "origValue");
      final List<InvestmentData> _result = new ArrayList<InvestmentData>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final InvestmentData _item;
        final Scheme _tmpScheme;
        if (! (_cursor.isNull(_cursorIndexOfId) && _cursor.isNull(_cursorIndexOfName) && _cursor.isNull(_cursorIndexOfType) && _cursor.isNull(_cursorIndexOfBank) && _cursor.isNull(_cursorIndexOfFundHouse) && _cursor.isNull(_cursorIndexOfMarket) && _cursor.isNull(_cursorIndexOfCurrentRate))) {
          _tmpScheme = new Scheme();
          final int _tmpId;
          _tmpId = _cursor.getInt(_cursorIndexOfId);
          _tmpScheme.setId(_tmpId);
          final String _tmpName;
          if (_cursor.isNull(_cursorIndexOfName)) {
            _tmpName = null;
          } else {
            _tmpName = _cursor.getString(_cursorIndexOfName);
          }
          _tmpScheme.setName(_tmpName);
          final String _tmpType;
          if (_cursor.isNull(_cursorIndexOfType)) {
            _tmpType = null;
          } else {
            _tmpType = _cursor.getString(_cursorIndexOfType);
          }
          _tmpScheme.setType(_tmpType);
          final String _tmpBank;
          if (_cursor.isNull(_cursorIndexOfBank)) {
            _tmpBank = null;
          } else {
            _tmpBank = _cursor.getString(_cursorIndexOfBank);
          }
          _tmpScheme.setBank(_tmpBank);
          final String _tmpFundHouse;
          if (_cursor.isNull(_cursorIndexOfFundHouse)) {
            _tmpFundHouse = null;
          } else {
            _tmpFundHouse = _cursor.getString(_cursorIndexOfFundHouse);
          }
          _tmpScheme.setFundHouse(_tmpFundHouse);
          final String _tmpMarket;
          if (_cursor.isNull(_cursorIndexOfMarket)) {
            _tmpMarket = null;
          } else {
            _tmpMarket = _cursor.getString(_cursorIndexOfMarket);
          }
          _tmpScheme.setMarket(_tmpMarket);
          final float _tmpCurrentRate;
          _tmpCurrentRate = _cursor.getFloat(_cursorIndexOfCurrentRate);
          _tmpScheme.setCurrentRate(_tmpCurrentRate);
        }  else  {
          _tmpScheme = null;
        }
        final Investment _tmpInvestment;
        if (! (_cursor.isNull(_cursorIndexOfSchemeId) && _cursor.isNull(_cursorIndexOfCurrValue) && _cursor.isNull(_cursorIndexOfOrigValue))) {
          _tmpInvestment = new Investment();
          final int _tmpSchemeId;
          _tmpSchemeId = _cursor.getInt(_cursorIndexOfSchemeId);
          _tmpInvestment.setSchemeId(_tmpSchemeId);
          final float _tmpCurrValue;
          _tmpCurrValue = _cursor.getFloat(_cursorIndexOfCurrValue);
          _tmpInvestment.setCurrValue(_tmpCurrValue);
          final float _tmpOrigValue;
          _tmpOrigValue = _cursor.getFloat(_cursorIndexOfOrigValue);
          _tmpInvestment.setOrigValue(_tmpOrigValue);
        }  else  {
          _tmpInvestment = null;
        }
        _item = new InvestmentData();
        _item.setScheme(_tmpScheme);
        _item.setInvestment(_tmpInvestment);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Investment getInvestment(final int schemeId) {
    final String _sql = "SELECT * FROM scheme_table INNER JOIN investment_table ON scheme_table.id = investment_table.schemeId WHERE schemeId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, schemeId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfSchemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "schemeId");
      final int _cursorIndexOfCurrValue = CursorUtil.getColumnIndexOrThrow(_cursor, "currValue");
      final int _cursorIndexOfOrigValue = CursorUtil.getColumnIndexOrThrow(_cursor, "origValue");
      final Investment _result;
      if(_cursor.moveToFirst()) {
        _result = new Investment();
        final int _tmpSchemeId;
        _tmpSchemeId = _cursor.getInt(_cursorIndexOfSchemeId);
        _result.setSchemeId(_tmpSchemeId);
        final float _tmpCurrValue;
        _tmpCurrValue = _cursor.getFloat(_cursorIndexOfCurrValue);
        _result.setCurrValue(_tmpCurrValue);
        final float _tmpOrigValue;
        _tmpOrigValue = _cursor.getFloat(_cursorIndexOfOrigValue);
        _result.setOrigValue(_tmpOrigValue);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public float getTotalInvestedAmount() {
    final String _sql = "SELECT SUM(origValue) FROM investment_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final float _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getFloat(0);
      } else {
        _result = 0f;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public float getTotalCurrentAmount() {
    final String _sql = "SELECT SUM(currValue) FROM investment_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final float _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getFloat(0);
      } else {
        _result = 0f;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Transaction> getAllTransactions(final int schemeId) {
    final String _sql = "SELECT * from transaction_table WHERE schemeId = ? ORDER BY date DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, schemeId);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfTransId = CursorUtil.getColumnIndexOrThrow(_cursor, "transId");
      final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final int _cursorIndexOfRate = CursorUtil.getColumnIndexOrThrow(_cursor, "rate");
      final int _cursorIndexOfAmount = CursorUtil.getColumnIndexOrThrow(_cursor, "amount");
      final int _cursorIndexOfSchemeId = CursorUtil.getColumnIndexOrThrow(_cursor, "schemeId");
      final List<Transaction> _result = new ArrayList<Transaction>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Transaction _item;
        _item = new Transaction();
        final int _tmpTransId;
        _tmpTransId = _cursor.getInt(_cursorIndexOfTransId);
        _item.setTransId(_tmpTransId);
        final String _tmpType;
        if (_cursor.isNull(_cursorIndexOfType)) {
          _tmpType = null;
        } else {
          _tmpType = _cursor.getString(_cursorIndexOfType);
        }
        _item.setType(_tmpType);
        final Date _tmpDate;
        final long _tmp;
        _tmp = _cursor.getLong(_cursorIndexOfDate);
        _tmpDate = DateConverter.toDate(_tmp);
        _item.setDate(_tmpDate);
        final float _tmpRate;
        _tmpRate = _cursor.getFloat(_cursorIndexOfRate);
        _item.setRate(_tmpRate);
        final float _tmpAmount;
        _tmpAmount = _cursor.getFloat(_cursorIndexOfAmount);
        _item.setAmount(_tmpAmount);
        final int _tmpSchemeId;
        _tmpSchemeId = _cursor.getInt(_cursorIndexOfSchemeId);
        _item.setSchemeId(_tmpSchemeId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
