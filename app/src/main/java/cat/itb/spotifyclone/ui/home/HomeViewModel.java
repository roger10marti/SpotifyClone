package cat.itb.spotifyclone.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import cat.itb.spotifyclone.api.ApiHelper;
import cat.itb.spotifyclone.model.Album;
import cat.itb.spotifyclone.model.Albumold;
import cat.itb.spotifyclone.model.Datum;

public class HomeViewModel extends ViewModel {


    private List<Albumold> albumes = new ArrayList<>();

    public HomeViewModel() {

        String busqueda = "roger";
        List<Datum> call = ApiHelper.lanzarPeticion("https://api.deezer.com/search/?q="+busqueda+"&index=0&limit=1&output=json");
        String title = call.get(0).getAlbum().getTitle();
        String caratula = call.get(0).getAlbum().getCoverMedium();
        Albumold a1 = new Albumold(title,caratula);
        Albumold a2 = new Albumold("Segundo",caratula);
        Albumold a3 = new Albumold("Tercero",caratula);
        Albumold a4 = new Albumold("Cuarto",caratula);
        Albumold a5 = new Albumold("Quinto",caratula);
        Albumold a6 = new Albumold("Sexto",caratula);

        albumes.add(a1);
        albumes.add(a2);
        albumes.add(a3);
        albumes.add(a4);
        albumes.add(a5);
        albumes.add(a6);
    }

    public int random() {
        return (int) (Math.random()*8+1);
    }

    public List<Albumold> getAlbumes() {
        return albumes;
    }

}