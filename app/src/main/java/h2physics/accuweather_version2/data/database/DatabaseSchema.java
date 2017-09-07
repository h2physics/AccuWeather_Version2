package h2physics.accuweather_version2.data.database;

/**
 * Created by YukiNoHara on 8/25/2017.
 */

public interface DatabaseSchema {
    //Weather
    public String TABLE_WEATHER = "weather";
    public String COLUMN_CURRENT_TEMP = "current_temp";
    public String COLUMN_MAX_TEMP = "max_temp";
    public String COLUMN_MIN_TEMP = "min_temp";
    public String COLUMN_PRESSURE = "pressure";
    public String COLUMN_HUMIDITY = "humidity";
    public String COLUMN_DESCRIPTION = "description";
    public String COLUMN_WIND = "wind";

    public String CREATE_TABLE_WEATHER = "CREATE TABLE " + TABLE_WEATHER + " (" +
            COLUMN_CURRENT_TEMP + " REAL NOT NULL," +
            COLUMN_MAX_TEMP + " REAL NOT NULL," +
            COLUMN_MIN_TEMP + " REAL NOT NULL," +
            COLUMN_PRESSURE + " REAL NOT NULL," +
            COLUMN_HUMIDITY + " REAL NOT NULL," +
            COLUMN_DESCRIPTION + " TEXT NOT NULL," +
            COLUMN_WIND + " REAL NOT NULL" + ");";
}
