package scott.android.com.marveltest.app.ui.login.presenter;

import com.facebook.GraphResponse;

import java.util.List;

import rx.Subscriber;
import scott.android.com.marveltest.base.presenter.BasePresenter;
import scott.android.com.marveltest.data.Injection;
import scott.android.com.marveltest.entities.Comic;
import scott.android.com.marveltest.entities.User;
import timber.log.Timber;

/**
 * @author pedroscott. scott7462@gmail.com
 * @version 1/15/17.
 *          <p>
 *          Copyright (C) 2015 The Android Open Source Project
 *          <p/>
 *          Licensed under the Apache License, Version 2.0 (the "License");
 *          you may not use this file except in compliance with the License.
 *          You may obtain a copy of the License at
 *          <p/>
 * @see <a href = "http://www.aprenderaprogramar.com" /> http://www.apache.org/licenses/LICENSE-2.0 </a>
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class SessionPresenter extends BasePresenter<SessionPresenterListener> {

    public void doLoginWithFacebook(GraphResponse response) {
        addSubscription(Injection.provideUserRepository()
                .saveUser(User.getUserToJson(response))
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e.toString());
                    }

                    @Override
                    public void onNext(User user) {
                        getViewListener().onUserLogin(user);
                    }
                }));
    }

    public void doGetProfile() {
        addSubscription(Injection.provideUserRepository().getUser()
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e.toString());
                    }

                    @Override
                    public void onNext(User user) {
                        getViewListener().onUserLogin(user);
                    }
                }));
    }

    public void doGetComic() {
        addSubscription(Injection.provideComicRepository().getComics()
                .subscribe(new Subscriber<List<Comic>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e.toString());
                    }

                    @Override
                    public void onNext(List<Comic> comics) {
                        Timber.e(comics.toString());
                    }
                }));
    }

    public void doLogout() {
        addSubscription(Injection.provideUserRepository()
                .logout()
                .subscribe(new Subscriber<Boolean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        getViewListener().onUserLogout();
                    }
                }));
    }
}
