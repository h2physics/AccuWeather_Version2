package h2physics.accuweather_version2.ui.forecast;

import android.database.Cursor;
import android.os.AsyncTask;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import h2physics.accuweather_version2.R;
import h2physics.accuweather_version2.adapter.WeatherAdapter;
import h2physics.accuweather_version2.data.database.DatabaseManager;
import h2physics.accuweather_version2.data.database.DatabaseSchema;
import h2physics.accuweather_version2.model.Weather;
import h2physics.accuweather_version2.network.NetworkCallback;
import h2physics.accuweather_version2.network.NetworkUtils;
import h2physics.accuweather_version2.ui.base.BaseFragment;
import h2physics.accuweather_version2.ui.customview.Toolbar;

/**
 * Created by YukiNoHara on 8/30/2017.
 */

public class ForecastFragment extends BaseFragment{
    @BindView(R.id.tv_location_today)
    TextView tvLocationToday;
    @BindView(R.id.tv_temperature_today)
    TextView tvTempToday;
    @BindView(R.id.tv_weather_today)
    TextView tvWeather;
    @BindView(R.id.wind)
    View wind;
    @BindView(R.id.real_temp)
    View realTemp;
    @BindView(R.id.humidity)
    View humidity;
    @BindView(R.id.pressure)
    View pressure;
    @BindView(R.id.lv_forecast)
    ListView lvForecast;

    private static final String LOG_TAG = ForecastFragment.class.getSimpleName();

    private WeatherAdapter mAdapter;
    private List<Weather> mList;
    private TextView tvTitleWind;
    private TextView tvStatisticWind;
    private ImageView imvDescriptionWind;
    private TextView tvTitleRealTemp;
    private TextView tvStatisticRealTemp;
    private ImageView imvDescriptionRealTemp;
    private TextView tvTitleHumidity;
    private TextView tvStatisticHumidity;
    private ImageView imvDescriptionHumidity;
    private TextView tvTitlePressure;
    private TextView tvStatisticPressure;
    private ImageView imvDescriptionPressure;

    @Override
    public int getContentLayout() {
        return R.layout.fragment_forecast;
    }

    @Override
    public void initView(View view) {
        tvTitleWind = wind.findViewById(R.id.tv_title);
        tvStatisticWind = wind.findViewById(R.id.tv_statistic);
        imvDescriptionWind = wind.findViewById(R.id.imv_description);
        tvTitleRealTemp = realTemp.findViewById(R.id.tv_title);
        tvStatisticRealTemp = realTemp.findViewById(R.id.tv_statistic);
        imvDescriptionRealTemp = realTemp.findViewById(R.id.imv_description);
        tvTitleHumidity = humidity.findViewById(R.id.tv_title);
        tvStatisticHumidity = humidity.findViewById(R.id.tv_statistic);
        imvDescriptionHumidity = humidity.findViewById(R.id.imv_description);
        tvTitlePressure = pressure.findViewById(R.id.tv_title);
        tvStatisticPressure = pressure.findViewById(R.id.tv_statistic);
        imvDescriptionPressure = pressure.findViewById(R.id.imv_description);

        mList = new ArrayList<>();
        mAdapter = new WeatherAdapter(getContext());


    }

    @Override
    public void initData() {
        tvTitleWind.setText(getString(R.string.wind));
        tvTitleRealTemp.setText(getString(R.string.real_temp));
        tvTitleHumidity.setText(getString(R.string.humidity));
        tvTitlePressure.setText(getString(R.string.pressure));
        imvDescriptionWind.setImageResource(R.drawable.ic_wind);
        imvDescriptionRealTemp.setImageResource(R.drawable.ic_temperature);
        imvDescriptionHumidity.setImageResource(R.drawable.ic_sun);
        imvDescriptionPressure.setImageResource(R.drawable.ic_pressure);
        new WeatherTask().execute("21.022736",
                                  "105.801944",
                                  "json",
                                  "metric",
                                  "7",
                                  "f99f7329e28b995c7bfd1a33f191b59b");


    }


    private class WeatherTask extends AsyncTask<String, Void, NetworkCallback> implements NetworkCallback{
        @Override
        protected void onPreExecute() {
//            showLoading("Hello", getView());
            Log.e(LOG_TAG, "OnPreExecute");
        }

        @Override
        protected NetworkCallback doInBackground(String... strings) {

            NetworkUtils.getWeather(getActivity(),
                    strings[0],
                    strings[1],
                    strings[2],
                    strings[3],
                    strings[4],
                    strings[5],
                    this);


            return null;
        }

        @Override
        protected void onPostExecute(NetworkCallback callback) {
            Log.e(LOG_TAG, "OnPostExecute");
        }

        @Override
        public void onSuccess() {
            Cursor cursor = DatabaseManager.queryWeather();
            if (cursor == null){
                return;
            }
            Log.e(LOG_TAG, "Cursor: " + cursor.toString());
            while (cursor.moveToNext()){
                Weather.Main main = new Weather.Main(
                        cursor.getDouble(cursor.getColumnIndex(DatabaseSchema.COLUMN_CURRENT_TEMP)),
                        cursor.getDouble(cursor.getColumnIndex(DatabaseSchema.COLUMN_MAX_TEMP)),
                        cursor.getDouble(cursor.getColumnIndex(DatabaseSchema.COLUMN_MIN_TEMP)),
                        cursor.getDouble(cursor.getColumnIndex(DatabaseSchema.COLUMN_HUMIDITY)),
                        cursor.getDouble(cursor.getColumnIndex(DatabaseSchema.COLUMN_PRESSURE)));
                Weather.WeatherDescription description = new Weather.WeatherDescription(
                        cursor.getString(cursor.getColumnIndex(DatabaseSchema.COLUMN_DESCRIPTION))
                );
                List<Weather.WeatherDescription> list = new ArrayList<>();
                list.add(description);
                Weather.Wind wind = new Weather.Wind(
                        cursor.getDouble(cursor.getColumnIndex(DatabaseSchema.COLUMN_WIND))
                );
                Weather weather = new Weather(main, list, wind);
                mList.add(weather);
            }
            tvTempToday.setText(String.valueOf(Math.round(mList.get(0).getMain().getRealTemp())) + "" + getString(R.string.celsius));
            tvWeather.setText(mList.get(0).getWeatherDescription().getDescription());
            tvStatisticWind.setText(String.valueOf(mList.get(0).getWind().getSpeed()));
            tvStatisticRealTemp.setText(String.valueOf(mList.get(0).getMain().getRealTemp()));
            tvStatisticHumidity.setText(String.valueOf(mList.get(0).getMain().getHumidity()));
            tvStatisticPressure.setText(String.valueOf(mList.get(0).getMain().getPressure()));
            mList.remove(0);
            mAdapter.clear();
            mAdapter.addAll(mList);
            Log.e(LOG_TAG, "List size: " + mList.size());
            lvForecast.setAdapter(mAdapter);
        }

        @Override
        public void onError() {

        }

        @Override
        public void onFailure() {

        }
    }
}
