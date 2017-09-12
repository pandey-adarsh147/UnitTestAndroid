package me.syncify.unittestandroid;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.CertificatePinner;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import okhttp3.Request;
import retrofit2.Retrofit;

/**
 * Created by adarshpandey on 9/4/17.
 */

@Module
public class RetrofitAdapter {

    @Provides
    @Singleton
    public GithubAPI provideGithubApi(Retrofit retrofit) {
        return retrofit.create(GithubAPI.class);
    }

    @Provides
    public OkHttpClient provideOkHttpClient(OkHttpClient.Builder builder) {
        builder.connectTimeout(7, TimeUnit.SECONDS)
                .readTimeout(120, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS);

        return builder.build();
    }

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpBuilder() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        List<Protocol> protocols = new ArrayList<>(1);
        protocols.add(Protocol.HTTP_1_1);

        builder.protocols(protocols);

        return builder;
    }

}
