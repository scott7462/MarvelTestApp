package scott.android.com.marveltest.data.source.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import scott.android.com.marveltest.R;
import scott.android.com.marveltest.data.source.db.tables.ComicTable;
import scott.android.com.marveltest.data.source.db.tables.ThumbnailTable;
import scott.android.com.marveltest.data.source.db.tables.UserTable;

/**
 * Created by Pedro Scott on 1/10/17.
 */

public class DBSQLiteHelper extends OrmLiteSqliteOpenHelper {

    private Dao<UserTable, Long> userTableDao;
    private Dao<ComicTable, Long> comicTableDao;

    public DBSQLiteHelper(Context context) {
        super(context, context.getString(R.string.db_name), null, context.getResources().getInteger(R.integer.db_version_number));
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, UserTable.class);
            TableUtils.createTable(connectionSource, ComicTable.class);
            TableUtils.createTable(connectionSource, ThumbnailTable.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource,
                          int oldVersion, int newVersion) {

        try {
            TableUtils.dropTable(connectionSource,UserTable.class,true);
            TableUtils.dropTable(connectionSource,ComicTable.class,true);
            TableUtils.dropTable(connectionSource,ThumbnailTable.class,true);
            TableUtils.createTable(connectionSource, UserTable.class);
            TableUtils.createTable(connectionSource, ComicTable.class);
            TableUtils.createTable(connectionSource, ThumbnailTable.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public Dao<UserTable, Long> getUserDao() throws SQLException {
        if (userTableDao == null) {
            userTableDao = getDao(UserTable.class);
        }
        return userTableDao;
    }

    public Dao<ComicTable, Long> getComicDao() throws SQLException {
        if (comicTableDao == null) {
            comicTableDao = getDao(ComicTable.class);
        }
        return comicTableDao;
    }


}
