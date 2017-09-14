package me.syncify.unittestandroid.keynotes;

import retrofit2.http.POST;

/**
 * Created by adarshpandey on 9/12/17.
 */

public interface KeyNoteAPI {

    @POST("/submit/keynote")
    KeyNote addKeyNotes(KeyNote keyNote);
}
