package me.syncify.unittestandroid.keynotes;

import android.app.Application;

/**
 * Created by shaishavsarawat on 14/09/17 at 2:08 PM
 */

public class UnitTestAndroidApplication extends Application {

    private static MockDataStore dataStore;

    public static MockDataStore getDataStore() {
        return dataStore;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        dataStore = new MockDataStore();
    }

}
