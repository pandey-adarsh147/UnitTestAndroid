package me.syncify.unittestandroid.keynotes;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by adarshpandey on 9/12/17.
 */

public class AddKeyNotePresenter implements AddKeyNoteProtocol.Presenter {

    private AddKeyNoteProtocol.View view;

    @Inject
    public KeyNoteAPI keyNoteAPI;

    AddKeyNotePresenter(AddKeyNoteProtocol.View view, KeyNoteAPI keyNoteAPI) {
        this.view = view;
        this.keyNoteAPI = keyNoteAPI;
    }

    @Override
    public void addNotes(KeyNote keyNote) {
        view.showLoader();
        KeyNote finalKeyNote = handleAddNoteApi(keyNote);
        view.hideLoader();
        if(finalKeyNote != null){
            view.showSuccess();
        } else {
            view.showError();
        }
    }

    @Override
    public KeyNote handleAddNoteApi(KeyNote keyNote) {
        return keyNoteAPI.addKeyNotes(keyNote);
    }

    @Override
    public List<KeyNote> getAllNotes() {
        return null;
    }



}
