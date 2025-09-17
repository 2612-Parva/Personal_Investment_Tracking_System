package com.investrack.app.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class DatabaseHelper_Impl extends DatabaseHelper {
  private volatile SchemeDao _schemeDao;

  private volatile PortfolioDao _portfolioDao;

  private volatile WatchlistDao _watchlistDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `scheme_table` (`id` INTEGER NOT NULL, `name` TEXT, `type` TEXT, `bank` TEXT, `fundHouse` TEXT, `market` TEXT, `currentRate` REAL NOT NULL, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `investment_table` (`schemeId` INTEGER NOT NULL, `currValue` REAL NOT NULL, `origValue` REAL NOT NULL, PRIMARY KEY(`schemeId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `transaction_table` (`transId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `type` TEXT, `date` INTEGER, `rate` REAL NOT NULL, `amount` REAL NOT NULL, `schemeId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `watchlist_table` (`schemeId` INTEGER NOT NULL, `date` INTEGER, PRIMARY KEY(`schemeId`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'fd348b6dbb8c4b371370cf51048a2146')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `scheme_table`");
        _db.execSQL("DROP TABLE IF EXISTS `investment_table`");
        _db.execSQL("DROP TABLE IF EXISTS `transaction_table`");
        _db.execSQL("DROP TABLE IF EXISTS `watchlist_table`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsSchemeTable = new HashMap<String, TableInfo.Column>(7);
        _columnsSchemeTable.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSchemeTable.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSchemeTable.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSchemeTable.put("bank", new TableInfo.Column("bank", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSchemeTable.put("fundHouse", new TableInfo.Column("fundHouse", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSchemeTable.put("market", new TableInfo.Column("market", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsSchemeTable.put("currentRate", new TableInfo.Column("currentRate", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysSchemeTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesSchemeTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoSchemeTable = new TableInfo("scheme_table", _columnsSchemeTable, _foreignKeysSchemeTable, _indicesSchemeTable);
        final TableInfo _existingSchemeTable = TableInfo.read(_db, "scheme_table");
        if (! _infoSchemeTable.equals(_existingSchemeTable)) {
          return new RoomOpenHelper.ValidationResult(false, "scheme_table(com.investrack.app.db.Scheme).\n"
                  + " Expected:\n" + _infoSchemeTable + "\n"
                  + " Found:\n" + _existingSchemeTable);
        }
        final HashMap<String, TableInfo.Column> _columnsInvestmentTable = new HashMap<String, TableInfo.Column>(3);
        _columnsInvestmentTable.put("schemeId", new TableInfo.Column("schemeId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentTable.put("currValue", new TableInfo.Column("currValue", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsInvestmentTable.put("origValue", new TableInfo.Column("origValue", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysInvestmentTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesInvestmentTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoInvestmentTable = new TableInfo("investment_table", _columnsInvestmentTable, _foreignKeysInvestmentTable, _indicesInvestmentTable);
        final TableInfo _existingInvestmentTable = TableInfo.read(_db, "investment_table");
        if (! _infoInvestmentTable.equals(_existingInvestmentTable)) {
          return new RoomOpenHelper.ValidationResult(false, "investment_table(com.investrack.app.db.Investment).\n"
                  + " Expected:\n" + _infoInvestmentTable + "\n"
                  + " Found:\n" + _existingInvestmentTable);
        }
        final HashMap<String, TableInfo.Column> _columnsTransactionTable = new HashMap<String, TableInfo.Column>(6);
        _columnsTransactionTable.put("transId", new TableInfo.Column("transId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionTable.put("type", new TableInfo.Column("type", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionTable.put("date", new TableInfo.Column("date", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionTable.put("rate", new TableInfo.Column("rate", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionTable.put("amount", new TableInfo.Column("amount", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTransactionTable.put("schemeId", new TableInfo.Column("schemeId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTransactionTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTransactionTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTransactionTable = new TableInfo("transaction_table", _columnsTransactionTable, _foreignKeysTransactionTable, _indicesTransactionTable);
        final TableInfo _existingTransactionTable = TableInfo.read(_db, "transaction_table");
        if (! _infoTransactionTable.equals(_existingTransactionTable)) {
          return new RoomOpenHelper.ValidationResult(false, "transaction_table(com.investrack.app.db.Transaction).\n"
                  + " Expected:\n" + _infoTransactionTable + "\n"
                  + " Found:\n" + _existingTransactionTable);
        }
        final HashMap<String, TableInfo.Column> _columnsWatchlistTable = new HashMap<String, TableInfo.Column>(2);
        _columnsWatchlistTable.put("schemeId", new TableInfo.Column("schemeId", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsWatchlistTable.put("date", new TableInfo.Column("date", "INTEGER", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysWatchlistTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesWatchlistTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoWatchlistTable = new TableInfo("watchlist_table", _columnsWatchlistTable, _foreignKeysWatchlistTable, _indicesWatchlistTable);
        final TableInfo _existingWatchlistTable = TableInfo.read(_db, "watchlist_table");
        if (! _infoWatchlistTable.equals(_existingWatchlistTable)) {
          return new RoomOpenHelper.ValidationResult(false, "watchlist_table(com.investrack.app.db.Watchlist).\n"
                  + " Expected:\n" + _infoWatchlistTable + "\n"
                  + " Found:\n" + _existingWatchlistTable);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "fd348b6dbb8c4b371370cf51048a2146", "3192e6a789fc9fe0f4149b6fe66b9023");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "scheme_table","investment_table","transaction_table","watchlist_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `scheme_table`");
      _db.execSQL("DELETE FROM `investment_table`");
      _db.execSQL("DELETE FROM `transaction_table`");
      _db.execSQL("DELETE FROM `watchlist_table`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(SchemeDao.class, SchemeDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(PortfolioDao.class, PortfolioDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(WatchlistDao.class, WatchlistDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public SchemeDao schemeDao() {
    if (_schemeDao != null) {
      return _schemeDao;
    } else {
      synchronized(this) {
        if(_schemeDao == null) {
          _schemeDao = new SchemeDao_Impl(this);
        }
        return _schemeDao;
      }
    }
  }

  @Override
  public PortfolioDao portfolioDao() {
    if (_portfolioDao != null) {
      return _portfolioDao;
    } else {
      synchronized(this) {
        if(_portfolioDao == null) {
          _portfolioDao = new PortfolioDao_Impl(this);
        }
        return _portfolioDao;
      }
    }
  }

  @Override
  public WatchlistDao watchlistDao() {
    if (_watchlistDao != null) {
      return _watchlistDao;
    } else {
      synchronized(this) {
        if(_watchlistDao == null) {
          _watchlistDao = new WatchlistDao_Impl(this);
        }
        return _watchlistDao;
      }
    }
  }
}
