package cat.itb.spotifyclone.api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cat.itb.spotifyclone.model.Consulta;
import cat.itb.spotifyclone.model.Datum;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiHelper {
    private static List<Datum> resposta;
    private static Retrofit retrofit;
    private static HttpLoggingInterceptor loggingInterceptor;
    private static OkHttpClient.Builder httpClientBuilder;

    public static List<Datum> lanzarPeticion(String url) {
        resposta = new ArrayList<>();
        loggingInterceptor = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder = new OkHttpClient.Builder().addInterceptor(loggingInterceptor);

        retrofit = new Retrofit.Builder().baseUrl("https://api.deezer.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClientBuilder.build())
                .build();

        WebServiceClient client = retrofit.create(WebServiceClient.class);
        Call<Consulta> call;
        call = client.getUrl(url);
        Thread t = new Thread(() -> {
            try {
                Response<Consulta> response = call.execute();
                resposta = response.body().getData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return resposta;
    }


}
