package scott.android.com.marveltest.data.repositories.comics.source;

import java.util.List;
import java.util.Random;

import rx.Observable;
import rx.functions.Func1;
import scott.android.com.marveltest.R;
import scott.android.com.marveltest.app.App;
import scott.android.com.marveltest.data.repositories.comics.ComicDataSource;
import scott.android.com.marveltest.data.source.rest.RestClientPublic;
import scott.android.com.marveltest.data.source.rest.response.DataResponse;
import scott.android.com.marveltest.data.source.rest.response.ResponseMarvel;
import scott.android.com.marveltest.entities.Comic;

/**
 * Created by Pedro Scott on 1/6/17.
 */
public class ComicRemote implements ComicDataSource {

    private static RestClientPublic restClientPublic;

    public static RestClientPublic getRestClientPublic() {
        return restClientPublic;
    }

    public ComicRemote() {
        restInt();
    }

    private void restInt() {
        restClientPublic = new RestClientPublic(App.getGlobalContext().getString(R.string.base_url));
    }


    public static ComicRemote newInstance() {
        return new ComicRemote();
    }

    @Override
    public Observable<List<Comic>> getComics() {
        Random random = new Random();
        return getRestClientPublic().getPublicService()
                .getComics(30, random.nextInt(300) + 1)
                .flatMap(new Func1<ResponseMarvel, Observable<List<Comic>>>() {
                    @Override
                    public Observable<List<Comic>> call(ResponseMarvel responseMarvel) {
                        return Observable.just(responseMarvel.getData().getComics());
                    }
                });
    }

    @Override
    public Observable<Comic> getComicDetail(int id) {
        return getRestClientPublic().getPublicService()
                .getComicDetails(id)
                .flatMap(new Func1<ResponseMarvel, Observable<Comic>>() {
                    @Override
                    public Observable<Comic> call(ResponseMarvel responseComic) {
                        return Observable.just(responseComic.getData().getComics().get(0));
                    }
                });
    }

    @Override
    public Observable<Comic> addOrRemoveFavorite(Comic comic) {
        return null;
    }

    @Override
    public Observable<Boolean> checkFavorite(int id) {
        return null;
    }

    @Override
    public Observable<List<Comic>> getFavorites() {
        return null;
    }
}
