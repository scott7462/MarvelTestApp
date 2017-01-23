package scott.android.com.marveltest.data;


import scott.android.com.marveltest.data.repositories.comics.ComicRepository;
import scott.android.com.marveltest.data.repositories.comics.source.ComicLocal;
import scott.android.com.marveltest.data.repositories.comics.source.ComicRemote;
import scott.android.com.marveltest.data.repositories.user.UserRepository;
import scott.android.com.marveltest.data.repositories.user.source.UserLocal;

/**
 * Created by Pedro Scott on 1/6/17.
 */

public class Injection {

    public static UserRepository provideUserRepository() {
        return UserRepository.newInstance(UserLocal.newInstance());
    }

    public static ComicRepository provideComicRepository() {
        return ComicRepository.newInstance(ComicLocal.newInstance(), ComicRemote.newInstance());
    }
}
