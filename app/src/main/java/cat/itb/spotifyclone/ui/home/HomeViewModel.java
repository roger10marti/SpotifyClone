package cat.itb.spotifyclone.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import cat.itb.spotifyclone.R;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private List<Album> albumes = new ArrayList<>();

    public HomeViewModel() {
        /*mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");*/

        Album a1 = new Album("Primero",R.drawable.ic_launcher_foreground);
        Album a2 = new Album("Segundo",R.drawable.ic_launcher_foreground);
        Album a3 = new Album("Tercero",R.drawable.ic_launcher_foreground);
        Album a4 = new Album("Cuarto",R.drawable.ic_launcher_foreground);
        Album a5 = new Album("Quinto",R.drawable.ic_launcher_foreground);
        Album a6 = new Album("Sexto",R.drawable.ic_launcher_foreground);

        albumes.add(a1);
        albumes.add(a2);
        albumes.add(a3);
        albumes.add(a4);
        albumes.add(a5);
        albumes.add(a6);
    }

    public List<Album> getAlbumes() {
        return albumes;
    }

    public LiveData<String> getText() {
        return mText;
    }
}