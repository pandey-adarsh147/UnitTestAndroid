package me.syncify.unittestandroid.keynotes;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by adarshpandey on 9/12/17.
 */

public class AddKeyNotePresenter implements AddKeyNoteProtocol.Presenter {

    AddKeyNoteProtocol.View view;

    @Inject
    KeyNoteAPI keyNoteAPI;

    public AddKeyNotePresenter(AddKeyNoteProtocol.View view, KeyNoteAPI keyNoteAPI) {
        this.view = view;
        this.keyNoteAPI = keyNoteAPI;
    }

    @Override
    public void addNotes(KeyNote keyNote) {
        view.showLoader();
        keyNoteAPI.addKeyNotes(keyNote);
    }

    @Override
    public List<KeyNote> getAllNotes() {
        return null;
    }



}
