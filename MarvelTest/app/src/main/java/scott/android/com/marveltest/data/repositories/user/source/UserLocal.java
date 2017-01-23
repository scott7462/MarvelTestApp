package scott.android.com.marveltest.data.repositories.user.source;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import scott.android.com.marveltest.app.App;
import scott.android.com.marveltest.data.repositories.user.UserDataSource;
import scott.android.com.marveltest.data.source.db.DBSQLiteHelper;
import scott.android.com.marveltest.data.source.db.tables.UserTable;
import scott.android.com.marveltest.entities.User;

/**
 * Created by Pedro Scott on 1/6/17.
 */
public class UserLocal implements UserDataSource {

    private static DBSQLiteHelper sqlExternalHelper;

    public static DBSQLiteHelper getDBSQLHelper() {
        if (sqlExternalHelper == null) {
            sqlExternalHelper = new DBSQLiteHelper(App.getGlobalContext());
        }
        return sqlExternalHelper;
    }

    @Override
    public Observable<User> saveUser(User user) {
        return Observable.just(user)
                .flatMap(new Func1<User, Observable<User>>() {
                    @Override
                    public Observable<User> call(User user) {
                        DBSQLiteHelper dbHelper = getDBSQLHelper();
                        try {
                            Dao<UserTable, Long> captionDao = dbHelper.getUserDao();
                            captionDao.createOrUpdate(UserTable.transformUserToUserTable(user));
                            return Observable.just(user);
                        } catch (SQLException e) {
                            e.printStackTrace();
                            return Observable.error(e);
                        }
                    }
                });
    }

    @Override
    public Observable<User> getUser() {
        return Observable.create(new Observable.OnSubscribe<User>() {
            @Override
            public void call(Subscriber<? super User> subscriber) {
                if (!subscriber.isUnsubscribed()) {
                    try {
                        Dao<UserTable, Long> userDao = getDBSQLHelper().getUserDao();
                        UserTable userTable = userDao.queryBuilder().query().get(0);
                        subscriber.onNext(UserTable.transformUserTableListToUser(userTable));
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
    public Observable<Boolean> logout() {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                try {
                    Dao<UserTable, Long> userDao = getDBSQLHelper().getUserDao();
                    userDao.deleteBuilder().delete();
                    subscriber.onNext(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                    subscriber.onError(e);
                }
                subscriber.onCompleted();
            }
        });
    }

    public static UserLocal newInstance() {
        return new UserLocal();
    }
}
