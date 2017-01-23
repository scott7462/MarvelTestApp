package scott.android.com.marveltest.data.repositories.comics.source;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import scott.android.com.marveltest.app.App;
import scott.android.com.marveltest.data.repositories.comics.ComicDataSource;
import scott.android.com.marveltest.data.source.db.DBConstraints;
import scott.android.com.marveltest.data.source.db.DBSQLiteHelper;
import scott.android.com.marveltest.data.source.db.tables.ComicTable;
import scott.android.com.marveltest.entities.Comic;

/**
 * Created by Pedro Scott on 1/6/17.
 */
public class ComicLocal implements ComicDataSource {

    private static DBSQLiteHelper sqlExternalHelper;

    public static DBSQLiteHelper getDBSQLHelper() {
        if (sqlExternalHelper == null) {
            sqlExternalHelper = new DBSQLiteHelper(App.getGlobalContext());
        }
        return sqlExternalHelper;
    }

    public static ComicLocal newInstance() {
        return new ComicLocal();
    }

    @Override
    public Observable<List<Comic>> getComics() {
        return null;
    }

    @Override
    public Observable<Comic> getComicDetail(int id) {
        return null;
    }

    @Override
    public Observable<Comic> addOrRemoveFavorite(final Comic comic) {
        return checkFavorite(comic.getId())
                .flatMap(new Func1<Boolean, Observable<Comic>>() {
                    @Override
                    public Observable<Comic> call(final Boolean aBoolean) {
                        return Observable.create(new Observable.OnSubscribe<Comic>() {
                            @Override
                            public void call(Subscriber<? super Comic> subscriber) {
                                if (!subscriber.isUnsubscribed()) {
                                    try {
                                        Dao<ComicTable, Long> comicTables = getDBSQLHelper().getComicDao();
                                        if (aBoolean) {
                                            comicTables.deleteById((long) comic.getId());
                                        } else {
                                            comicTables.createOrUpdate(ComicTable.transformComic(comic));
                                        }
                                        subscriber.onNext(comic);
                                    } catch (SQLException e) {
                                        subscriber.onError(e);
                                        e.printStackTrace();
                                    }
                                    subscriber.onCompleted();
                                }
                            }
                        });
                    }
                });
    }

    @Override
    public Observable<Boolean> checkFavorite(final int id) {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    try {
                        Dao<ComicTable, Long> comicTables = getDBSQLHelper().getComicDao();
                        List<ComicTable> comicTable = comicTables.queryBuilder().where()
                                .eq(DBConstraints.TABLE_COMIC_ID,id).query();
                        subscriber.onNext(comicTable.size() > 0);
                    } catch (SQLException e) {
                        subscriber.onError(e);
                        e.printStackTrace();
                    }
                    subscriber.onCompleted();
                }
            }
        });
    }

    @Override
    public Observable<List<Comic>> getFavorites() {
        return Observable.create(new Observable.OnSubscribe<List<Comic>>() {
            @Override
            public void call(Subscriber<? super List<Comic>> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    try {
                        Dao<ComicTable, Long> comicDao = getDBSQLHelper().getComicDao();
                        List<ComicTable> comicTables = comicDao.queryBuilder().query();
                        subscriber.onNext(ComicTable.tansforListComic(comicTables));
                    } catch (SQLException e) {
                        subscriber.onError(e);
                        e.printStackTrace();
                    }
                    subscriber.onCompleted();
                }
            }
        });
    }
}
