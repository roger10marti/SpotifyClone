package cat.itb.spotifyclone.ui.secondaryfragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cat.itb.spotifyclone.R;
import cat.itb.spotifyclone.api.ApiHelper;
import cat.itb.spotifyclone.model.Album;
import cat.itb.spotifyclone.model.AlbumSimple;
import cat.itb.spotifyclone.model.Song;
import cat.itb.spotifyclone.model.Tracks;

public class FragmentAlbumList extends Fragment {

    private RecyclerView recyclerView;
    private SongsAdapter songsAdapter;
    private MaterialToolbar toolbar;
    private TextView albumTitle;
    private ImageView albumPic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_album_list, container, false);
        albumTitle = v.findViewById(R.id.titleAlbum);
        albumPic = v.findViewById(R.id.albumPhoto);
        AlbumSimple albumSimple = getArguments().getParcelable("album");
        Picasso.with(getContext()).load(albumSimple.getCover()).into(albumPic);
        albumTitle.setText(albumSimple.getTitle());

        Album album = ApiHelper.consultarAlbum(albumSimple.getId());
        List<Song> tracks = album.getTracks().getData();



        songsAdapter = new SongsAdapter(tracks);

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