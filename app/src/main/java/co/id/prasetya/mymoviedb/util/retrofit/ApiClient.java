package co.id.prasetya.mymoviedb.util.retrofit;

import android.content.Context;

import co.id.prasetya.mymoviedb.util.Constanta;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static Retrofit retrofit = null;

    private static HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    private static OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

    public static Retrofit newInstance(Context context) {

        if (retrofit == null) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);

            retrofit = new Retrofit.Builder().baseUrl(Constanta.BASE_DISCOVER_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(httpClient.build())
                    .build();
        }

        return retrofit;
    }
}
