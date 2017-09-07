package h2physics.accuweather_version2;

import android.app.Application;

import h2physics.accuweather_version2.data.database.DatabaseManager;

/**
 * Created by YukiNoHara on 8/25/2017.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        DatabaseManager.init(this);
    }
}
