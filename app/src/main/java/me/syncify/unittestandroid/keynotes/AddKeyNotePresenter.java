package me.syncify.unittestandroid.keynotes;

import android.os.Handler;

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

        if (success) {
            // BAM!
            view.showSuccess();
        } else {
            // DAMN!
            view.showError();
        }

        // Hide the loader post a delay
        (new Handler()).postDelayed(() -> view.hideLoader(), 2000);
    }

    // Mah API call
    public boolean handleAddNote(KeyNote keyNote) {
        // Lol fake api call
        keyNoteAPI.addKeyNote(keyNote);

        // Adding to the mock data store in Application class
        MockDataStore dataStore = UnitTestAndroidApplication.getDataStore();
        return dataStore.addNote(keyNote);
    }

    @Override
    public List<KeyNote> getAllNotes() {
        view.showLoader();
        // Lol fake api call
        List<KeyNote> notes = keyNoteAPI.getAllKeyNotes();

        // Hide the loader post a delay
        (new Handler()).postDelayed(() -> view.hideLoader(), 1000);

        MockDataStore dataStore = UnitTestAndroidApplication.getDataStore();
        return dataStore.getAllNotes();
    }

    @Override
    public List<KeyNote> getAllNotes(int noteCount) {
        if (noteCount >= 0) {
            view.showLoader();
            // Lol fake api call
            List<KeyNote> notes = keyNoteAPI.getAllKeyNotes();

            // Hide the loader post a delay
            (new Handler()).postDelayed(() -> view.hideLoader(), 1000);

            MockDataStore dataStore = UnitTestAndroidApplication.getDataStore();
            List<KeyNote> allNotes = dataStore.getAllNotes();

            List<KeyNote> trueNotes = new ArrayList<>(noteCount);
            for (int i = 0; i < noteCount; i++) {
                trueNotes.add(allNotes.get(i));
            }

            return trueNotes;
        }

        // Hide the loader post a delay
        (new Handler()).postDelayed(() -> view.hideLoader(), 1000);

        return new LinkedList<>();
    }

}
