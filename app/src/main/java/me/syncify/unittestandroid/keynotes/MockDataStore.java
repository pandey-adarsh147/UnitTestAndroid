package me.syncify.unittestandroid.keynotes;

import android.text.TextUtils;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by shaishavsarawat on 14/09/17 at 1:52 PM
 */

public class MockDataStore {

    private LinkedHashMap<Long, KeyNote> noteMap;

    public MockDataStore() {
        noteMap = new LinkedHashMap<>();
    }

    // Add a note
    public boolean addNote(KeyNote keynote) {
        // Adding only if not already added and the title is non-empty
        if (!noteMap.containsKey(keynote.id) && !TextUtils.isEmpty(keynote.title)) {
            noteMap.put(keynote.id, keynote);
            return true;
        }

        return false;
    }

    // Remove all notes
    public void clearNotes() {
        noteMap.clear();
    }

    // Get all notes as a list
    public List<KeyNote> getAllNotes() {
        return new LinkedList<>(noteMap.values());
    }

}
