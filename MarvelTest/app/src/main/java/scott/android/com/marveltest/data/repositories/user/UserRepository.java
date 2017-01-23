package scott.android.com.marveltest.data.repositories.user;

import com.facebook.login.LoginManager;

import rx.Observable;
import rx.functions.Func1;
import scott.android.com.marveltest.entities.User;
import scott.android.com.marveltest.preferences.PreferenceUtils;

/**
 * Created by Pedro Scott on 1/6/17.
 */

public class UserRepository implements UserDataSource {

    UserDataSource local;

    public UserRepository(UserDataSource local) {
        this.local = local;
    }


    @Override
    public Observable<User> saveUser(User user) {
        return local.saveUser(user)
                .flatMap(new Func1<User, Observable<User>>() {
                    @Override
                    public Observable<User> call(User user) {
                        PreferenceUtils.saveSession(true);
                        return Observable.just(user);
                    }
                });
    }

    @Override
    public Observable<User> getUser() {
        return local.getUser();
    }

    @Override
    public Observable<Boolean> logout() {
        return local.logout()
                .flatMap(new Func1<Boolean, Observable<Boolean>>() {
                    @Override
                    public Observable<Boolean> call(Boolean aBoolean) {
                        LoginManager.getInstance().logOut();
                        PreferenceUtils.saveSession(false);
                        return Observable.just(aBoolean);
                    }
                });
    }

    public static UserRepository newInstance(UserDataSource local) {
        return new UserRepository(local);
    }
}
