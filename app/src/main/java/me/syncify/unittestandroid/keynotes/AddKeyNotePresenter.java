package me.syncify.unittestandroid.keynotes;

import java.util.ArrayList;
import java.util.LinkedList;
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
    public void addNote(KeyNote keyNote) {
        view.showLoader();
        boolean success = handleAddNote(keyNote);

        view.hideLoader();
        if (success) {
            // BAM!
            view.showSuccess();
        } else {
            // DAMN!
            view.showError();
        }
    }

    // Mah API call
    public boolean handleAddNote(KeyNote keyNote) {
        return keyNoteAPI.addKeyNote(keyNote);
    }

    @Override
    public List<KeyNote> getAllNotes() {
        view.showLoader();
        List<KeyNote> notes = keyNoteAPI.getAllKeyNotes();
        view.hideLoader();
        return notes;
    }

    @Override
    public List<KeyNote> getAllNotes(int noteCount) {
        view.showLoader();
        List<KeyNote> notes = keyNoteAPI.getAllKeyNotes();
        view.hideLoader();

        List<KeyNote> trueNotes = new ArrayList<>(noteCount);

        for (int i = 0; i < noteCount; i++) {
            trueNotes.add(new KeyNote());
        }

        return trueNotes;
    }

}
