package co.classplus.app.cms.data.network;

import android.os.Build;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import co.classplus.app.cms.BuildConfig;
import co.classplus.app.cms.data.network.retrofit.RxErrorHandlingCallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkSingleton {

    private static NetworkSingleton instance = null;
    private Retrofit retrofit;

    private NetworkSingleton() {
    }

    public static NetworkSingleton getInstance() {

        if (instance == null) {
            instance = new NetworkSingleton();
        }
        return instance;
    }

    Retrofit getNetworkClient() {
        if (retrofit == null) {
            OkHttpClient.Builder builder = new OkHttpClient.Builder();

            // add logging for debugging
            if (BuildConfig.IS_DEBUG) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                builder.networkInterceptors().add(interceptor);
            }

            // adding custom headers for every call
            builder.addInterceptor(chain -> {
                // intercepting every call and adding custom header
                Request original = chain.request();
                Request request = original.newBuilder()
                        .header("User-Agent", "Mobile-Android")
                        .header("Device-Details", Build.BRAND + "_" + Build.MODEL + "_SDK-" + Build.VERSION.SDK_INT)
                        .method(original.method(), original.body())
                        .build();
                return chain.proceed(request);
            });
            builder.connectTimeout(10, TimeUnit.SECONDS);
            builder.readTimeout(10, TimeUnit.SECONDS);
            builder.writeTimeout(10, TimeUnit.SECONDS);
            OkHttpClient okHttpClient = builder.build();

            Gson gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        return retrofit;
    }
}