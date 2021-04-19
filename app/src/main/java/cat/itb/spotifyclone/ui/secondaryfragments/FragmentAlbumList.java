package cat.itb.spotifyclone.ui.secondaryfragments;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.IBinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.MaterialToolbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import cat.itb.spotifyclone.PlayerActivity;
import cat.itb.spotifyclone.R;
import cat.itb.spotifyclone.api.ApiHelper;
import cat.itb.spotifyclone.mediaplayer.Constants;
import cat.itb.spotifyclone.mediaplayer.MusicService;
import cat.itb.spotifyclone.model.Album;
import cat.itb.spotifyclone.model.AlbumSimple;
import cat.itb.spotifyclone.model.Song;


public class FragmentAlbumList extends Fragment {

    private RecyclerView recyclerView;
    private SongsAdapter songsAdapter;
    private MaterialToolbar toolbar;
    private Button aleatorioButton;
    private TextView albumTitle, titleConfig, artistName;
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
        titleConfig = v.findViewById(R.id.textoConfiguracion);
        artistName = v.findViewById(R.id.textoArtista);
        aleatorioButton = v.findViewById(R.id.aleatoriobutton);

        Album album = getArguments().getParcelable("album");
        Picasso.with(getContext()).load(album.getCover()).into(albumPic);
        albumTitle.setText(album.getTitle());
        titleConfig.setText(album.getTitle());
        String[] year = album.getReleaseDate().split("-");
        artistName.append(album.getArtist().getName()+" · "+year[0]);

        List<Song> tracks = album.getTracks().getData();

        songsAdapter = new SongsAdapter(tracks,album);

        recyclerView = v.findViewById(R.id.recyclerSongs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(songsAdapter);

        aleatorioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlayerActivity.class);
                intent.putExtra("id",album.getId());
                intent.putExtra("pos",0);
                startActivity(intent);
            }
        });
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