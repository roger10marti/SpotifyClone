package cat.itb.spotifyclone.ui.home;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import cat.itb.spotifyclone.R;
import cat.itb.spotifyclone.model.Album;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private List<Album> albumes = new ArrayList<>();

    public HomeViewModel() {
        /*mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");*/

        Album a1 = new Album("Primero",random());
        Album a2 = new Album("Segundo",random());
        Album a3 = new Album("Tercero",random());
        Album a4 = new Album("Cuarto",random());
        Album a5 = new Album("Quinto",random());
        Album a6 = new Album("Sexto",random());

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

    public List<Album> getAlbumes() {
        return albumes;
    }

    public LiveData<String> getText() {
        return mText;
    }
}