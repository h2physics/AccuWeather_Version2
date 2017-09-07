package h2physics.accuweather_version2.network;

import h2physics.accuweather_version2.model.WeatherList;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by YukiNoHara on 9/4/2017.
 */

public interface IWeather {
    @GET("?")
    Call<WeatherList> getWeatherList(@Query("lat") String lat,
                                     @Query("lon") String lon,
                                     @Query("mode") String mode,
                                     @Query("units") String units,
                                     @Query("cnt") String day,
                                     @Query("APPID") String apiKey);
}
