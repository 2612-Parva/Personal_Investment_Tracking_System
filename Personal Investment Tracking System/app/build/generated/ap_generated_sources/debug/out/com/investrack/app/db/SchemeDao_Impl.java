package com.investrack.app.db;

import android.database.Cursor;
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
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class SchemeDao_Impl implements SchemeDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Scheme> __insertionAdapterOfScheme;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public SchemeDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfScheme = new EntityInsertionAdapter<Scheme>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR IGNORE INTO `scheme_table` (`id`,`name`,`type`,`bank`,`fundHouse`,`market`,`currentRate`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Scheme value) {
        stmt.bindLong(1, value.getId());
        if (value.getName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getName());
        }
        if (value.getType() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getType());
        }
        if (value.getBank() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getBank());
        }
        if (value.getFundHouse() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getFundHouse());
        }
        if (value.getMarket() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getMarket());
        }
        stmt.bindDouble(7, value.getCurrentRate());
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM scheme_table";
        return _query;
      }
    };
  }

  @Override
  public void insert(final Scheme scheme) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfScheme.insert(scheme);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void insertAll(final List<Scheme> schemes) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfScheme.insert(schemes);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void clearAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfClearAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfClearAll.release(_stmt);
    }
  }

  @Override
  public List<Scheme> getAllSchemes() {
    final String _sql = "SELECT * from scheme_table ORDER BY name ASC";
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
      final List<Scheme> _result = new ArrayList<Scheme>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Scheme _item;
        _item = new Scheme();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        _item.setName(_tmpName);
        final String _tmpType;
        if (_cursor.isNull(_cursorIndexOfType)) {
          _tmpType = null;
        } else {
          _tmpType = _cursor.getString(_cursorIndexOfType);
        }
        _item.setType(_tmpType);
        final String _tmpBank;
        if (_cursor.isNull(_cursorIndexOfBank)) {
          _tmpBank = null;
        } else {
          _tmpBank = _cursor.getString(_cursorIndexOfBank);
        }
        _item.setBank(_tmpBank);
        final String _tmpFundHouse;
        if (_cursor.isNull(_cursorIndexOfFundHouse)) {
          _tmpFundHouse = null;
        } else {
          _tmpFundHouse = _cursor.getString(_cursorIndexOfFundHouse);
        }
        _item.setFundHouse(_tmpFundHouse);
        final String _tmpMarket;
        if (_cursor.isNull(_cursorIndexOfMarket)) {
          _tmpMarket = null;
        } else {
          _tmpMarket = _cursor.getString(_cursorIndexOfMarket);
        }
        _item.setMarket(_tmpMarket);
        final float _tmpCurrentRate;
        _tmpCurrentRate = _cursor.getFloat(_cursorIndexOfCurrentRate);
        _item.setCurrentRate(_tmpCurrentRate);
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
