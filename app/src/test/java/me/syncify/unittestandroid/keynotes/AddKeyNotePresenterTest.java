package me.syncify.unittestandroid.keynotes;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import javax.inject.Inject;

import static org.mockito.Mockito.verify;

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


    @Test
    public void testAddKeyNotes() {
        AddKeyNoteProtocol.Presenter presenter = new AddKeyNotePresenter(view, keyNoteAPI);

        KeyNote keyNote = new KeyNote();
        presenter.addNotes(keyNote);

        verify(view).showLoader();
        verify(keyNoteAPI).addKeyNotes(keyNote);
    }

}