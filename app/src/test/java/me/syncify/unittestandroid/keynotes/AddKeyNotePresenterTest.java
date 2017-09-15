package me.syncify.unittestandroid.keynotes;

import android.os.Build;
import android.os.Handler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.List;
import java.util.Random;

import me.syncify.unittestandroid.BuildConfig;

import static org.mockito.Mockito.verify;

/**
 * Created by adarshpandey on 9/12/17.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP_MR1,
        application = UnitTestAndroidApplication.class)
@RunWith(RobolectricTestRunner.class)
public class AddKeyNotePresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    public AddKeyNoteProtocol.View view;

    @Mock
    public KeyNoteAPI keyNoteAPI;

    private AddKeyNoteProtocol.Presenter presenter;

    @Before
    public void setup() {
        presenter = new AddKeyNotePresenter(view, keyNoteAPI);
    }

    @Test
    public void testAddKeyNotesSuccess() {
        KeyNote keyNote = new KeyNote();
        keyNote.id = System.currentTimeMillis();
        keyNote.title = String.valueOf(System.currentTimeMillis());

        presenter.addNote(keyNote);

        verify(view).showLoader();
        verify(keyNoteAPI).addKeyNote(keyNote);
        verify(view).showSuccess();

        (new Handler()).postDelayed(() -> verify(view).hideLoader(), 2000);
    }

    @Test
    public void testAddKeyNotesFailure() {
        KeyNote keyNote = new KeyNote();

        presenter.addNote(keyNote);

        verify(view).showLoader();
        verify(keyNoteAPI).addKeyNote(keyNote);
        verify(view).showError();

        (new Handler()).postDelayed(() -> verify(view).hideLoader(), 2000);
    }

    @Test
    public void testShowAllNotes() {
        presenter.getAllNotes();

        verify(view).showLoader();
        verify(keyNoteAPI).getAllKeyNotes();

        (new Handler()).postDelayed(() -> verify(view).hideLoader(), 2000);
    }

    @Test
    public void testShowAllNotesCount() {
        int count = 10;

        for (int i = 0; i < count; i++) {
            long random = new Random().nextInt() + System.currentTimeMillis();
            KeyNote keyNote = new KeyNote();
            keyNote.id = random;
            keyNote.title = String.valueOf(random);
            presenter.addNote(keyNote);
        }

        List<KeyNote> list1 = presenter.getAllNotes(count);
        verify(keyNoteAPI).getAllKeyNotes();

        (new Handler()).postDelayed(() -> verify(view).hideLoader(), 2000);

        Assert.assertTrue(list1.size() == count);
    }

}