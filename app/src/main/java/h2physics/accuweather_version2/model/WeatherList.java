package h2physics.accuweather_version2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by YukiNoHara on 9/5/2017.
 */

public class WeatherList {
    @SerializedName("list")
    private List<Weather> list;

    public List<Weather> getList() {
        return list;
    }
}
