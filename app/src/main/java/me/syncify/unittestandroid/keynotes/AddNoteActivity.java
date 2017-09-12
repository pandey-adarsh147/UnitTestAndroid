package me.syncify.unittestandroid.keynotes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by adarshpandey on 9/12/17.
 */

public class AddNoteActivity extends AppCompatActivity implements AddKeyNoteProtocol.View {

    AddKeyNoteProtocol.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // View

        presenter = new AddKeyNotePresenter(this, null);
    }

    @Override
    public void showLoader() {

    }

    @Override
    public void showSuccess() {

    }

    @Override
    public void hideLoader() {

    }

    @Override
    public void showError() {

    }
}
