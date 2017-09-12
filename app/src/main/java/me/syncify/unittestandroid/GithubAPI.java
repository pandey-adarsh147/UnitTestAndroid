package me.syncify.unittestandroid;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by adarshpandey on 9/4/17.
 */

public interface GithubAPI {

    @GET("/get/gethub")
    Observable<GithubDto> getGithubDto();
}
