package me.syncify.unittestandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Button;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 25)
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertThat(4).isEqualTo(2 + 2);
    }

    @Test
    public void clickButton_shouldChangeResultsViewText() throws Exception {

//        TestOpenHelper testOpenHelper = new TestOpenHelper(RuntimeEnvironment.application, "path", null, 1);

        TestOpenHelper helper = new TestOpenHelper(null, null, null, 1);
        SQLiteDatabase database = helper.getReadableDatabase();
        assertDatabaseOpened(database, helper);
        assertInitialDB(database, helper);

        MainActivity mainActivity = Robolectric.setupActivity(MainActivity.class);

        Button button = (Button) mainActivity.findViewById(R.id.performClick);
        TextView textView = (TextView) mainActivity.findViewById(R.id.result);

        button.performClick();
        assertThat(textView.getText().toString()).isEqualTo("Main hoon");
    }

    private static void assertInitialDB(SQLiteDatabase database, TestOpenHelper helper) {
        assertDatabaseOpened(database, helper);
        assertThat(helper.onCreateCalled).isTrue();
    }

    private static void assertSubsequentDB(SQLiteDatabase database, TestOpenHelper helper) {
        assertDatabaseOpened(database, helper);
        assertThat(helper.onCreateCalled).isFalse();
    }

    private static void assertDatabaseOpened(SQLiteDatabase database, TestOpenHelper helper) {
        assertThat(database).isNotNull();
        assertThat(database.isOpen()).isTrue();
        assertThat(helper.onOpenCalled).isTrue();
        assertThat(helper.onUpgradeCalled).isFalse();
    }

    private class TestOpenHelper extends SQLiteOpenHelper {
        public boolean onCreateCalled;
        public boolean onUpgradeCalled;
        public boolean onOpenCalled;

        public TestOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase database) {
            onCreateCalled = true;
        }

        @Override
        public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
            onUpgradeCalled = true;
        }

        @Override
        public void onOpen(SQLiteDatabase database) {
            onOpenCalled = true;
        }

        @Override
        public synchronized void close() {
            onCreateCalled = false;
            onUpgradeCalled = false;
            onOpenCalled = false;

            super.close();
        }
    }
}