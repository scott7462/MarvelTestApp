package scott.android.com.marveltest.data.repositories.comics;

import java.util.List;

import rx.Observable;
import rx.Subscription;
import scott.android.com.marveltest.entities.Comic;

/**
 * Created by Pedro Scott on 1/10/17.
 */

public interface ComicDataSource {

    Observable<List<Comic>> getComics();

    Observable<Comic> getComicDetail(int id);

    Observable<Comic> addOrRemoveFavorite(Comic comic);

    Observable<Boolean> checkFavorite(int comic);

    Observable<List<Comic>> getFavorites();
}



