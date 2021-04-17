package cat.itb.spotifyclone.api;

import java.util.List;


import cat.itb.spotifyclone.model.Album;
import cat.itb.spotifyclone.model.Consulta;
import cat.itb.spotifyclone.model.Song;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface WebServiceClient {
    @GET()
    Call<Consulta> getUrl(@Url String url);

    @GET()
    Call<Album> getAlbum(@Url String url);
}
