package cat.itb.spotifyclone.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import cat.itb.spotifyclone.model.Albumold;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private List<Albumold> albumes = new ArrayList<>();

    public HomeViewModel() {
        /*mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");*/

        Albumold a1 = new Albumold("Primero",random());
        Albumold a2 = new Albumold("Segundo",random());
        Albumold a3 = new Albumold("Tercero",random());
        Albumold a4 = new Albumold("Cuarto",random());
        Albumold a5 = new Albumold("Quinto",random());
        Albumold a6 = new Albumold("Sexto",random());

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

    public LiveData<String> getText() {
        return mText;
    }
}