package me.syncify.unittestandroid.keynotes;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by adarshpandey on 9/12/17.
 */

public interface KeyNoteAPI {

    @POST("/submit/keynote")
    boolean addKeyNote(KeyNote keyNote);

    @GET("/keynotes/all")
    List<KeyNote> getAllKeyNotes();

}
