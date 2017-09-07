package h2physics.accuweather_version2.network;

import android.content.ContentValues;
import android.content.Context;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Scanner;

import h2physics.accuweather_version2.data.database.DatabaseManager;
import h2physics.accuweather_version2.data.database.DatabaseSchema;
import h2physics.accuweather_version2.data.database.DatabaseUtils;
import h2physics.accuweather_version2.model.Weather;
import h2physics.accuweather_version2.model.WeatherList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by YukiNoHara on 9/5/2017.
 */

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    public static void getWeather(Context context, String lat, String lon, String mode, String units, String day, String apiKey, final NetworkCallback callback){
        IWeather iWeather = RetrofitService.getRetrofitInstance(context).create(IWeather.class);
        Call<WeatherList> call = iWeather.getWeatherList(lat, lon, mode, units, day, apiKey);
        call.enqueue(new Callback<WeatherList>() {
            @Override
            public void onResponse(Call<WeatherList> call, Response<WeatherList> response) {
                if (response.isSuccessful() && response.code() == 200){
                    Log.e(LOG_TAG, "On Success");
                    Log.e(LOG_TAG, response.body().getList().get(0).toString());
                    List<Weather> list = response.body().getList();
                    DatabaseManager.clearAndStopData();
                    DatabaseUtils.insertListWeather(list);
                    callback.onSuccess();
                } else {
                    callback.onError();
                    Log.e(LOG_TAG, "On error");
                }
            }

            @Override
            public void onFailure(Call<WeatherList> call, Throwable t) {
                callback.onFailure();
                Log.e(LOG_TAG, "On failure");
            }
        });
    }

//    public static String fetchWeatherNormally(URL url) throws IOException {
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        try {
//            InputStream in = connection.getInputStream();
//            Scanner scanner = new Scanner(in);
//            scanner.useDelimiter("\\A");
//            boolean hasInput = scanner.hasNext();
//            if (hasInput){
//                return scanner.next();
//            } else {
//                return null;
//            }
//        } finally {
//            connection.disconnect();
//        }
//    }
}
