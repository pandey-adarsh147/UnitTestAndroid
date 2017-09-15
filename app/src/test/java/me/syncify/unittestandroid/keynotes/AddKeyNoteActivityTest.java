package me.syncify.unittestandroid.keynotes;

import android.os.Build;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

import java.util.Random;

import me.syncify.unittestandroid.BuildConfig;
import me.syncify.unittestandroid.R;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by adarshpandey on 9/12/17.
 */
@Config(constants = BuildConfig.class, sdk = Build.VERSION_CODES.LOLLIPOP_MR1,
        application = UnitTestAndroidApplication.class)
@RunWith(RobolectricTestRunner.class)
public class AddKeyNoteActivityTest {

    private ActivityController<AddNoteActivity> controller;
    private AddNoteActivity activity;

    @Before
    public void setup() {
        controller = Robolectric.buildActivity(AddNoteActivity.class);

        activity = controller
                .create()
                .start()
                .resume()
                .visible()
                .get();
    }

    @After
    public void tearDown() {
        UnitTestAndroidApplication.getDataStore().clearNotes();

        // Obliterate activity
        controller
                .pause()
                .stop()
                .destroy();
    }

    @Test
    public void testInvalidAddKeyNote() {
        // Trying to add invalid note
        Button button = (Button) activity.findViewById(R.id.button_add_note);
        button.performClick();

        View overlayLayout = activity.findViewById(R.id.overlay_layout);
        TextView overlayText = (TextView) activity.findViewById(R.id.overlay_text);

        assertTrue("Loader Not visible", overlayLayout.getVisibility() == View.VISIBLE);
        assertTrue("Not showing Error in fail case", overlayText.getText().equals(activity.getString(R.string.cannot_add_note)));

        (new Handler()).postDelayed(() -> {
            assertTrue("Loader did not remove", overlayLayout.getVisibility() == View.INVISIBLE);
        }, 3000);
    }

    @Test
    public void testValidAddKeyNote() {
        // Trying to add valid note
        EditText titleEditText = (EditText) activity.findViewById(R.id.edittext_title);
        titleEditText.setText(String.valueOf(System.currentTimeMillis()));

        Button button = (Button) activity.findViewById(R.id.button_add_note);
        button.performClick();

        View overlayLayout = activity.findViewById(R.id.overlay_layout);
        TextView overlayText = (TextView) activity.findViewById(R.id.overlay_text);

        assertTrue("Loader Not visible", overlayLayout.getVisibility() == View.VISIBLE);
        assertTrue("Not showing Success in success case", overlayText.getText().equals(activity.getString(R.string.successfully_added_note)));

        (new Handler()).postDelayed(() -> {
            assertTrue("Loader did not remove", overlayLayout.getVisibility() == View.INVISIBLE);
        }, 3000);
    }

    @Test
    public void testSameNoteAddedTwice() {
        EditText titleEditText = (EditText) activity.findViewById(R.id.edittext_title);
        titleEditText.setText(String.valueOf(System.currentTimeMillis()));

        Button button = (Button) activity.findViewById(R.id.button_add_note);
        button.performClick();

        // Adding the same note again
        button.performClick();

        View overlayLayout = activity.findViewById(R.id.overlay_layout);
        TextView overlayText = (TextView) activity.findViewById(R.id.overlay_text);

        assertTrue("Loader Not visible", overlayLayout.getVisibility() == View.VISIBLE);
        assertTrue("Not showing Error in fail case", overlayText.getText().equals(activity.getString(R.string.cannot_add_note)));
        ;

        (new Handler()).postDelayed(() -> {
            assertTrue("Loader did not remove", overlayLayout.getVisibility() == View.INVISIBLE);
        }, 3000);
    }

    @Test
    public void testGetNotesWithoutAdding() {
        Button button = (Button) activity.findViewById(R.id.button_get_notes);
        button.performClick();

        View overlayLayout = activity.findViewById(R.id.overlay_layout);

        assertTrue("Loader Not visible", overlayLayout.getVisibility() == View.VISIBLE);
        assertTrue("Getting Notes without adding", activity.recyclerAdapter.getItemCount() == 0);

        (new Handler()).postDelayed(() -> {
            assertTrue("Loader did not remove", overlayLayout.getVisibility() == View.INVISIBLE);
        }, 3000);
    }


    @Test
    public void testGetNotesWithNotesAdded() {
        Button buttonGetNotes = (Button) activity.findViewById(R.id.button_get_notes);
        buttonGetNotes.performClick();

        int initialCount = activity.recyclerView.getChildCount();

        EditText titleEditText = (EditText) activity.findViewById(R.id.edittext_title);
        Button button = (Button) activity.findViewById(R.id.button_add_note);

        int count = 1 + new Random().nextInt(10);

        for (int i = 0; i < count; i++) {
            titleEditText.setText(String.valueOf(System.currentTimeMillis() + new Random().nextInt()));
            button.performClick();
        }

        buttonGetNotes.performClick();

        View overlayLayout = activity.findViewById(R.id.overlay_layout);

        assertTrue("Loader Not visible", overlayLayout.getVisibility() == View.VISIBLE);
        assertTrue("Getting Notes without adding", activity.recyclerView.getChildCount() == initialCount + count);

        (new Handler()).postDelayed(() -> {
            assertTrue("Loader did not remove", overlayLayout.getVisibility() == View.INVISIBLE);
        }, 3000);
    }

    @Test
    public void testNotesClicksAfterAdd() {
        EditText titleEditText = (EditText) activity.findViewById(R.id.edittext_title);
        Button button = (Button) activity.findViewById(R.id.button_add_note);

        int count = 1 + new Random().nextInt(10);
        String[] strings = new String[count];

        for (int i = 0; i < count; i++) {
            strings[i] = String.valueOf(System.currentTimeMillis() + new Random().nextInt());
            titleEditText.setText(strings[i]);
            button.performClick();
        }

        Button buttonGetNotes = (Button) activity.findViewById(R.id.button_get_notes);
        buttonGetNotes.performClick();

        int randomInt = new Random().nextInt(count);
        activity.recyclerView.getChildAt(randomInt).performClick();

        assertThat("Toast not visible", ShadowToast.getTextOfLatestToast(), equalTo("Title: " + strings[randomInt]));
    }

}