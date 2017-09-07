package h2physics.accuweather_version2.network;

import android.content.Context;

import java.io.IOException;

import h2physics.accuweather_version2.BuildConfig;
import h2physics.accuweather_version2.R;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by YukiNoHara on 9/4/2017.
 */

public class RetrofitService {
    public static Retrofit getRetrofitInstance(Context context){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request originalRequest = chain.request();
                        Request.Builder requestBuilder = originalRequest
                                .newBuilder()
                                .addHeader("Accept", "application/json");
                        Request newRequest = requestBuilder.build();
                        return chain.proceed(newRequest);
                    }
                });

        if (BuildConfig.DEBUG){
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        return new Retrofit.Builder()
                .baseUrl(context.getString(R.string.base_url))
                .addConverterFactory(GsonConverterFactory.create())
                .callFactory(builder.build())
                .build();
    }
}
