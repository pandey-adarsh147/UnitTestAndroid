package me.syncify.unittestandroid.notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import me.syncify.unittestandroid.BuildConfig;

import static org.mockito.Mockito.when;

/**
 * Created by adarshpandey on 9/11/17.
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 25)
public class AddNoteDaoTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    Context context;
    @Before
    public void setup() {
        context = RuntimeEnvironment.application;
        setupHelperMock();
    }

    /**
     * Setup a DBSchema mock
     * @return  Returns the mocked obj
     */
    private DBSchema setupHelperMock(){
        // create the mocks
        DBSchema helperM = Mockito.mock(DBSchema.class);
        SQLiteDatabase dbM = Mockito.mock(SQLiteDatabase.class);

        // Define method's results for the mock obj
        when(helperM.getReadableDatabase()).thenReturn(dbM);
        when(helperM.getWritableDatabase()).thenReturn(dbM);
        return helperM;
    }

    @Test
    public void testAddNote() throws Exception {
        Note note = new Note();
        note.id = 1;
        note.title = "Hello";
        note.desc = "World";


    }

    public class DBSchema extends SQLiteOpenHelper {

        public DBSchema(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }
}