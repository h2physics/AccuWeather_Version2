package h2physics.accuweather_version2.data.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

/**
 * Created by YukiNoHara on 8/25/2017.
 */

public enum  DatabaseManager {
    INSTANCE;

    public static final String DATABASE_NAME = "weather.db";
    public static final int DATABASE_VERSION = 3;
    private static final String LOG_TAG = DatabaseManager.class.getSimpleName();

    private static DatabaseHelper mHelper;

    /**
     * This method should be called when application starts
     * @param context
     */
    public static void init(Context context){
        if (INSTANCE.mHelper == null){
            INSTANCE.mHelper = new DatabaseHelper(context);
        }
    }

    public static void clearAndStopData(){
        mHelper.getWritableDatabase().delete(DatabaseSchema.TABLE_WEATHER, null, null);
    }

    public static void insertWeather(ContentValues value){
        mHelper.getWritableDatabase().insert(DatabaseSchema.TABLE_WEATHER, null, value);
        Log.e(LOG_TAG, "Inserted successfully");
    }

    public static Cursor queryWeather(){
        Cursor c = null;
        c = mHelper.getReadableDatabase()
                .query(DatabaseSchema.TABLE_WEATHER,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null);
        return c;
    }

    public static void updateWeather(){

    }

    public static void bulkInsert(ContentValues[] values){
        for (ContentValues cv : values){
            insertWeather(cv);
        }
    }
}
