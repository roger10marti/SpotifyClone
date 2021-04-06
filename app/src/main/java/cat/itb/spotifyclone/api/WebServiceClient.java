package cat.itb.spotifyclone.api;

import cat.itb.spotifyclone.model.Consulta;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WebServiceClient {
    @GET()
    Call<Consulta> getUrl(@Url String url);
}
