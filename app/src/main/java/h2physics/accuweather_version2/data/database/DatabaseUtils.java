package h2physics.accuweather_version2.data.database;

import android.content.ContentValues;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import h2physics.accuweather_version2.model.Weather;

/**
 * Created by YukiNoHara on 9/5/2017.
 */

public class DatabaseUtils {
    public static void insertListWeather(List<Weather> list){
        List<ContentValues> values = new ArrayList<>();
        for (Weather w : list){
            ContentValues cv = new ContentValues();
            cv.put(DatabaseSchema.COLUMN_CURRENT_TEMP, w.getMain().getRealTemp());
            cv.put(DatabaseSchema.COLUMN_MAX_TEMP, w.getMain().getMaxTemp());
            cv.put(DatabaseSchema.COLUMN_MIN_TEMP, w.getMain().getMinTemp());
            cv.put(DatabaseSchema.COLUMN_HUMIDITY, w.getMain().getHumidity());
            cv.put(DatabaseSchema.COLUMN_PRESSURE, w.getMain().getPressure());
            cv.put(DatabaseSchema.COLUMN_DESCRIPTION, w.getWeatherDescription().getDescription());
            cv.put(DatabaseSchema.COLUMN_WIND, w.getWind().getSpeed());
            values.add(cv);
        }
        DatabaseManager.bulkInsert(values.toArray(new ContentValues[7]));
    }


}
