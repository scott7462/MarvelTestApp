package scott.android.com.marveltest.data.source.rest;


import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;
import scott.android.com.marveltest.data.source.rest.response.DataResponse;
import scott.android.com.marveltest.data.source.rest.response.ResponseMarvel;

/**
 * Created by Pedro Scott
 */
public interface PublicServiceInterface {

    @GET("comics")
    Observable<ResponseMarvel> getComics(@Query("limit") int limit,
                                         @Query("offset") int offset);
    @GET("comics/{id}")
    Observable<ResponseMarvel> getComicDetails(@Path("id") int id);
}
