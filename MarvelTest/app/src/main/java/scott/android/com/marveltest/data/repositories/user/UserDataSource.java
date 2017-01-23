package scott.android.com.marveltest.data.repositories.user;

import rx.Observable;
import scott.android.com.marveltest.entities.User;

/**
 * Created by Pedro Scott
 *on 1/6/17.
 */

public interface UserDataSource {

    Observable<User> saveUser(User user);

    Observable<User> getUser();

    Observable<Boolean> logout();
}
