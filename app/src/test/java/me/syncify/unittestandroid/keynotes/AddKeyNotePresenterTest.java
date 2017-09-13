package me.syncify.unittestandroid.keynotes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by adarshpandey on 9/12/17.
 */
public class AddKeyNotePresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    public AddKeyNoteProtocol.View view;

    @Mock
    public KeyNoteAPI keyNoteAPI;

    AddKeyNoteProtocol.Presenter presenter;

    @Before
    public void setup() {
        presenter = new AddKeyNotePresenter(view, keyNoteAPI);
    }


    @Test
    public void testAddKeyNotesSuccess() {
        KeyNote keyNote = new KeyNote();

        // Mock success
        when(((AddKeyNotePresenter) presenter).handleAddNote(keyNote)).thenReturn(true);

        presenter.addNote(keyNote);

        verify(view).showLoader();
        verify(keyNoteAPI).addKeyNote(keyNote);
        verify(view).hideLoader();
        verify(view).showSuccess();
    }

    @Test
    public void testAddKeyNotesFailure() {
        KeyNote keyNote = new KeyNote();

        // Mock Trump's presidency
        when(((AddKeyNotePresenter) presenter).handleAddNote(keyNote)).thenReturn(false);

        presenter.addNote(keyNote);

        verify(view).showLoader();
        verify(keyNoteAPI).addKeyNote(keyNote);
        verify(view).hideLoader();
        verify(view).showError();
    }

    @Test
    public void testShowAllNotes() {
        presenter.getAllNotes();

        verify(view).showLoader();
        verify(keyNoteAPI).getAllKeyNotes();
        verify(view).hideLoader();
    }

    @Test
    public void testShowAllNotesCount() {
        int count = 10;
        List<KeyNote> list1 = presenter.getAllNotes(count);
        verify(view).showLoader();
        verify(keyNoteAPI).getAllKeyNotes();
        verify(view).hideLoader();

        Assert.assertTrue(list1.size() == count);
    }

}