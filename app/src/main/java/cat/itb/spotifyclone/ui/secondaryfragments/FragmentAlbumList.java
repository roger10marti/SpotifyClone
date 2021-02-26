package cat.itb.spotifyclone.ui.secondaryfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

import cat.itb.spotifyclone.R;
import cat.itb.spotifyclone.model.Song;

public class FragmentAlbumList extends Fragment {

    RecyclerView recyclerView;
    SongsAdapter songsAdapter;
    List<Song> songList;
    private MaterialToolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_album_list, container, false);
        TextView titleAlbum = v.findViewById(R.id.titleAlbum);

        titleAlbum.setText(getArguments().getString("title"));

        songList = new ArrayList<>();

        String[] titulos = {"Daidis","Ekisde","En didac","Dídac amb accent","Emerald Sword","Epicus Furor","Eternal Glroy","Wings of Destiny","The Dark Tower of Abyss","Symphony of the Enchanced Lands"};
        String[] albumes = {"Daidis","Daidis","Daidis","Daidis","Rhapsody","Rhapsody","Rhapsody","Rhapsody","Rhapsody","Rhapsody"};

        for (int i = 0; i<albumes.length; i++) {
            Song s = new Song();
            s.setTitle(titulos[i]);
            s.setAlbum(albumes[i]);
            songList.add(s);
        }

        songsAdapter = new SongsAdapter(songList);

        recyclerView = v.findViewById(R.id.recyclerSongs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(songsAdapter);

        toolbar = v.findViewById(R.id.topAppBar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).popBackStack();
            }
        });

        return v;
    }
}