package com.investrack.app.db;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
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
public final class WatchlistDao_Impl implements WatchlistDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Watchlist> __insertionAdapterOfWatchlist;

  private final EntityDeletionOrUpdateAdapter<Watchlist> __deletionAdapterOfWatchlist;

  public WatchlistDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfWatchlist = new EntityInsertionAdapter<Watchlist>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `watchlist_table` (`schemeId`,`date`) VALUES (?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Watchlist value) {
        stmt.bindLong(1, value.getSchemeId());
        final long _tmp = DateConverter.fromDate(value.getDate());
        stmt.bindLong(2, _tmp);
      }
    };
    this.__deletionAdapterOfWatchlist = new EntityDeletionOrUpdateAdapter<Watchlist>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `watchlist_table` WHERE `schemeId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Watchlist value) {
        stmt.bindLong(1, value.getSchemeId());
      }
    };
  }

  @Override
  public void add(final Watchlist watchlist) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfWatchlist.insert(watchlist);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Watchlist watchlist) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfWatchlist.handle(watchlist);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<WatchlistData> getWatchlist() {
    final String _sql = "SELECT scheme_table.*, watchlist_table.date as date FROM scheme_table INNER JOIN watchlist_table ON scheme_table.id = watchlist_table.schemeId ORDER BY watchlist_table.date DESC";
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
      final int _cursorIndexOfDate = CursorUtil.getColumnIndexOrThrow(_cursor, "date");
      final List<WatchlistData> _result = new ArrayList<WatchlistData>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final WatchlistData _item;
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
        _item = new WatchlistData();
        final Date _tmpDate;
        final long _tmp;
        _tmp = _cursor.getLong(_cursorIndexOfDate);
        _tmpDate = DateConverter.toDate(_tmp);
        _item.setDate(_tmpDate);
        _item.setScheme(_tmpScheme);
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
