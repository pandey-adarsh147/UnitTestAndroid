package me.syncify.unittestandroid.keynotes;

import java.util.List;

/**
 * Created by adarshpandey on 9/12/17.
 */

public class AddKeyNoteProtocol {
    public interface View {
        void showLoader();
        void showSuccess();
        void hideLoader();
        void showError();
    }

    public interface Presenter {
        void addNotes(KeyNote keyNote);
        KeyNote handleAddNoteApi(KeyNote keyNote);
        List<KeyNote> getAllNotes();
    }
}
