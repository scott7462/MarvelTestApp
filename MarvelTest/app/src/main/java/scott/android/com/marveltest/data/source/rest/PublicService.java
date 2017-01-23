package scott.android.com.marveltest.data.source.rest;

import rx.Observable;
import scott.android.com.marveltest.data.source.rest.response.DataResponse;
import scott.android.com.marveltest.data.source.rest.response.ResponseMarvel;

/**
 * Created by Julian Cardona on 11/12/14.
 */
public class PublicService {


    private PublicServiceInterface apiService;

    public PublicService(PublicServiceInterface apiService) {
        this.apiService = apiService;
    }

    public Observable<ResponseMarvel> getComics(int limit,int offset) {
        return apiService.getComics(limit,offset);
    }

    public Observable<ResponseMarvel> getComicDetails(int id) {
        return apiService.getComicDetails(id);
    }
}
