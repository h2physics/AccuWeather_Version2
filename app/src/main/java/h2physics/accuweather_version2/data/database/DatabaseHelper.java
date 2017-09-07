package h2physics.accuweather_version2.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by YukiNoHara on 8/25/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper implements DatabaseSchema {

    public DatabaseHelper(Context context) {
        super(context, DatabaseManager.DATABASE_NAME, null, DatabaseManager.DATABASE_VERSION);
    }

    @Override
    public synchronized void close() {
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.beginTransaction();
            sqLiteDatabase.execSQL(CREATE_TABLE_WEATHER);
            sqLiteDatabase.setTransactionSuccessful();
        } finally {
            sqLiteDatabase.endTransaction();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_WEATHER + ";");
        onCreate(sqLiteDatabase);
    }
}
