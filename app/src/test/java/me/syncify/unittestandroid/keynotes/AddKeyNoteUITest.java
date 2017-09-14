package me.syncify.unittestandroid.keynotes;

import android.os.Build;
import android.view.View;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import me.syncify.unittestandroid.BuildConfig;
import me.syncify.unittestandroid.R;
import me.syncify.unittestandroid.keynotes.activity.AddNoteActivity;

/**
 * <p>
 * Created by vilakshansaxena on 9/14/17.
 * </p>
 */

@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP)
@RunWith(RobolectricTestRunner.class)
public class AddKeyNoteUITest {

    private AddNoteActivity activity;

    @Before
    public void setup() {
        activity = Robolectric.setupActivity(AddNoteActivity.class);
    }

    @Test
    public void test_NoteListUIWithData() {
        List<KeyNote> keyNoteList = getKeyNoteList();
        activity.showNoteList(keyNoteList);

        Assert.assertEquals("Note recycler should be visible",
                View.VISIBLE, activity.findViewById(R.id.recycler_notes).getVisibility());
        Assert.assertEquals("Empty text should be hidden",
                View.GONE, activity.findViewById(R.id.empty_text).getVisibility());
    }

    @Test
    public void test_NoteListUIWithEmptyData() {
        activity.showEmptyList();

        Assert.assertEquals("Note recycler should be hidden",
                View.GONE, activity.findViewById(R.id.recycler_notes).getVisibility());
        Assert.assertEquals("Empty text should be visible",
                View.VISIBLE, activity.findViewById(R.id.empty_text).getVisibility());
    }

    private List<KeyNote> getKeyNoteList() {
        List<KeyNote> keyNoteList = new ArrayList<>();
        KeyNote keyNote = new KeyNote();
        keyNote.title = "Key Note 1";
        keyNoteList.add(keyNote);
        keyNote = new KeyNote();
        keyNote.title = "Key Note 2";
        keyNoteList.add(keyNote);
        keyNote = new KeyNote();
        keyNote.title = "Key Note 3";
        keyNoteList.add(keyNote);
        keyNote = new KeyNote();
        keyNote.title = "Key Note 4";
        keyNoteList.add(keyNote);
        keyNote = new KeyNote();
        keyNote.title = "Key Note 5";
        keyNoteList.add(keyNote);
        keyNote = new KeyNote();
        keyNote.title = "Key Note 6";
        keyNoteList.add(keyNote);
        return keyNoteList;
    }
}
