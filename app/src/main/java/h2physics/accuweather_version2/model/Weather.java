package h2physics.accuweather_version2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by YukiNoHara on 9/3/2017.
 */

public class Weather {
    @SerializedName("main")
    private Main main;

    @SerializedName("weather")
    private List<WeatherDescription> weatherDescriptions;

    @SerializedName("wind")
    private Wind wind;

    public Weather(Main main, List<WeatherDescription> weatherDescriptions, Wind wind) {
        this.main = main;
        this.weatherDescriptions = weatherDescriptions;
        this.wind = wind;
    }

    public Main getMain() {
        return main;
    }

    public WeatherDescription getWeatherDescription() {
        return weatherDescriptions.get(0);
    }

    public Wind getWind() {
        return wind;
    }

    public static class Main{
        @SerializedName("temp")
        private double realTemp;
        @SerializedName("temp_max")
        private double maxTemp;
        @SerializedName("temp_min")
        private double minTemp;
        @SerializedName("pressure")
        private double pressure;
        @SerializedName("humidity")
        private double humidity;

        public Main(double realTemp, double maxTemp, double minTemp, double pressure, double humidity) {
            this.realTemp = realTemp;
            this.maxTemp = maxTemp;
            this.minTemp = minTemp;
            this.pressure = pressure;
            this.humidity = humidity;
        }

        public double getRealTemp() {
            return realTemp;
        }

        public double getMaxTemp() {
            return maxTemp;
        }

        public double getMinTemp() {
            return minTemp;
        }

        public double getPressure() {
            return pressure;
        }

        public double getHumidity() {
            return humidity;
        }
    }

    public static class WeatherDescription{
        @SerializedName("main")
        private String mDescription;

        public WeatherDescription(String mDescription) {
            this.mDescription = mDescription;
        }

        public String getDescription() {
            return mDescription;
        }
    }

    public static class Wind{
        @SerializedName("speed")
        private double speed;

        public Wind(double speed) {
            this.speed = speed;
        }

        public double getSpeed() {
            return speed;
        }
    }
}

