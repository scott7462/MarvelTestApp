package scott.android.com.marveltest.app.ui.main.presenter;

import java.util.List;

import rx.Subscriber;
import scott.android.com.marveltest.base.presenter.BasePresenter;
import scott.android.com.marveltest.data.Injection;
import scott.android.com.marveltest.entities.Comic;
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


public class MainPresenter extends BasePresenter<MainPresenterListener> {

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
                        getViewListener().itemsLoaded(comics);
                    }
                }));
    }
}
