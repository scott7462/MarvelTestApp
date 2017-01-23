package scott.android.com.marveltest.data.repositories.comics;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import scott.android.com.marveltest.entities.Comic;

/**
 * Created by Pedro Scott on 1/6/17.
 */

public class ComicRepository implements ComicDataSource {

    ComicDataSource local;
    ComicDataSource remote;

    public ComicRepository(ComicDataSource local, ComicDataSource remote) {
        this.local = local;
        this.remote = remote;
    }

    public static ComicRepository newInstance(ComicDataSource local, ComicDataSource remote) {
        return new ComicRepository(local, remote);
    }

    @Override
    public Observable<List<Comic>> getComics() {
        return remote.getComics()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<Comic> getComicDetail(int id) {
        return remote.getComicDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Observable<Comic> addOrRemoveFavorite(Comic comic) {
        return local.addOrRemoveFavorite(comic);
    }

    @Override
    public Observable<Boolean> checkFavorite(int id) {
        return local.checkFavorite(id);
    }

    @Override
    public Observable<List<Comic>> getFavorites() {
        return local.getFavorites()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


}
