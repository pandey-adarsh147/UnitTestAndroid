package me.syncify.unittestandroid.keynotes;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jakewharton.rxbinding.view.RxView;

import java.util.List;

import me.syncify.unittestandroid.R;

/**
 * Created by adarshpandey on 9/12/17.
 */

public class AddNoteActivity extends AppCompatActivity implements AddKeyNoteProtocol.View {

    private AddKeyNoteProtocol.Presenter presenter;
    private List<KeyNote> keyNotes;
    public RecyclerView recyclerView;
    public RecyclerAdapter recyclerAdapter;

    private TextView overlayText;
    private View overlayLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_notes);

        presenter = new AddKeyNotePresenter(this, new KeyNoteAPI() {
            @Override
            public boolean addKeyNote(KeyNote keyNote) {
                return false;
            }

            @Override
            public List<KeyNote> getAllKeyNotes() {
                return null;
            }
        });

        init();
    }

    private void init() {
        recyclerView = (RecyclerView) findViewById(R.id.recycler);

        overlayText = (TextView) findViewById(R.id.overlay_text);
        overlayLayout = findViewById(R.id.overlay_layout);

        // Setup get all notes button
        Button getNotesButton = (Button) findViewById(R.id.button_get_notes);
        RxView.clicks(getNotesButton).subscribe(o -> {
            getNotes();
        });

        // Setup Add Note button
        Button addNoteButton = (Button) findViewById(R.id.button_add_note);
        RxView.clicks(addNoteButton).subscribe(o -> {
            addNote();
        });
    }

    public void getNotes() {
        keyNotes = presenter.getAllNotes();
        recyclerAdapter = new RecyclerAdapter(AddNoteActivity.this, keyNotes);
        recyclerView.setLayoutManager(new LinearLayoutManager(AddNoteActivity.this));
        recyclerView.setAdapter(recyclerAdapter);
    }


    public void addNote() {
        KeyNote keyNote = new KeyNote();

        String title = ((EditText) findViewById(R.id.edittext_title)).getText().toString();
        String desc = ((EditText) findViewById(R.id.edittext_desc)).getText().toString();

        int id = (TextUtils.isEmpty(title) ? 0 : title.hashCode()) +
                (TextUtils.isEmpty(desc) ? 0 : desc.hashCode());

        keyNote.id = id;
        keyNote.title = title;
        keyNote.desc = desc;

        presenter.addNote(keyNote);
    }

    @Override
    public void showLoader() {
        overlayLayout.setVisibility(View.VISIBLE);
        overlayText.setText(R.string.loading);
    }

    @Override
    public void showSuccess() {
        overlayLayout.setVisibility(View.VISIBLE);
        overlayText.setText(R.string.successfully_added_note);
    }

    @Override
    public void hideLoader() {
        overlayLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showError() {
        overlayLayout.setVisibility(View.VISIBLE);
        overlayText.setText(R.string.cannot_add_note);
    }
}
