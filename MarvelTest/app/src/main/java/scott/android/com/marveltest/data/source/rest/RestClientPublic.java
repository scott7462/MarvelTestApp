package scott.android.com.marveltest.data.source.rest;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import org.joda.time.DateTime;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import scott.android.com.marveltest.R;
import scott.android.com.marveltest.app.App;
import scott.android.com.marveltest.utils.SecurityUtils;

/**
 * Copyright (C) 2015 The Android Open Source Project
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public class RestClientPublic {
    private PublicService publicService;
    private PublicServiceInterface apiService;

    public RestClientPublic(String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClient())
                .build();
        this.apiService = retrofit.create(PublicServiceInterface.class);

        this.publicService = new PublicService(apiService);
    }


    public PublicService getPublicService() {
        return publicService;
    }

    private OkHttpClient getClient() {
        OkHttpClient.Builder httpClient =
                new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.interceptors().add(interceptor);
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                HttpUrl originalHttpUrl = original.url();
                String ts = String.valueOf(DateTime.now().getMillis());
                String publicKey = App.getGlobalContext().getString(R.string.marvel_public);
                String privateKey = App.getGlobalContext().getString(R.string.marvel_private);

                HttpUrl url = null;
                try {
                    url = originalHttpUrl.newBuilder()
                            .addQueryParameter(App.getGlobalContext().getString(R.string.api_key),
                                    publicKey)
                            .addQueryParameter(App.getGlobalContext().getString(R.string.ts),
                                    ts)
                            .addQueryParameter(App.getGlobalContext().getString(R.string.hash),
                                    SecurityUtils.generateMD5(new StringBuilder().append(ts)
                                            .append(privateKey).append(publicKey).toString()))
                            .build();
                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }

                Request.Builder requestBuilder = original.newBuilder()
                        .url(url);
                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });
        httpClient.networkInterceptors().add(new StethoInterceptor());
        return httpClient.build();
    }

}
