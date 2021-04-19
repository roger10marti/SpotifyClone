package cat.itb.spotifyclone.ui.home;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import cat.itb.spotifyclone.api.ApiHelper;
import cat.itb.spotifyclone.model.AlbumSimple;

import cat.itb.spotifyclone.model.Datum;

public class HomeViewModel extends ViewModel {



    private List<AlbumSimple> albumSimples = new ArrayList<>();

    public HomeViewModel() {
        String busqueda = "megadeth";
        List<Datum> call = ApiHelper.lanzarPeticion(busqueda);

        for (Datum datum : call) {
            albumSimples.add(datum.getAlbumSimple());
        }
        /*
        String title1 = call.get(0).getAlbum().getTitle();
        String caratula1 = call.get(0).getAlbum().getCoverMedium();
        String title = call.get(1).getAlbum().getTitle();
        String caratula = call.get(1).getAlbum().getCoverMedium();
        String title2 = call.get(2).getAlbum().getTitle();
        String caratula2 = call.get(2).getAlbum().getCoverMedium();
        String title3 = call.get(3).getAlbum().getTitle();
        String caratula3 = call.get(3).getAlbum().getCoverMedium();
        String title4 = call.get(4).getAlbum().getTitle();
        String caratula4 = call.get(4).getAlbum().getCoverMedium();
        String title5 = call.get(5).getAlbum().getTitle();
        String caratula5 = call.get(5).getAlbum().getCoverMedium();
        Albumold a1 = new Albumold(title1,caratula1);
        Albumold a2 = new Albumold(title,caratula);
        Albumold a3 = new Albumold(title2,caratula2);
        Albumold a4 = new Albumold(title3,caratula3);
        Albumold a5 = new Albumold(title4,caratula4);
        Albumold a6 = new Albumold(title5,caratula5);

        albumes.add(a1);
        albumes.add(a2);
        albumes.add(a3);
        albumes.add(a4);
        albumes.add(a5);
        albumes.add(a6);*/
    }


    /*
    public List<Albumold> getAlbumes() {
        return albumes;
    }*/

    public List<AlbumSimple> getAlbumSimples() {
        return albumSimples;
    }
}