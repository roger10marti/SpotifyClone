package cat.itb.spotifyclone.ui.library;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import cat.itb.spotifyclone.PlayerActivity;
import cat.itb.spotifyclone.R;
import cat.itb.spotifyclone.model.FavouriteSong;
import cat.itb.spotifyclone.ui.search.SearchingAdapter;

public class ListFavouriteSongs extends Fragment {

    private DatabaseReference dref;
    FirebaseDatabase firebaseDatabase;
    private List<FavouriteSong> favouriteSongs = new ArrayList<>();

    private RecyclerView recyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_favourite_songs, container, false);

        firebaseDatabase = FirebaseDatabase.getInstance();
        dref = firebaseDatabase.getReference("Favourites");
        recyclerView = v.findViewById(R.id.recyclerSongs);

        Query q = dref.orderByKey();
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot post : snapshot.getChildren()) {
                    if (post.exists()) {
                        FavouriteSong f = new FavouriteSong();
                        f.setSong(post.child("song").getValue().toString());
                        f.setCover(post.child("cover").getValue().toString());
                        f.setFecha(post.child("fecha").getValue().toString());
                        f.setPreview(post.child("preview").getValue().toString());
                        f.setArtist(post.child("artist").getValue().toString());

                        System.out.println(post.child("song").getValue().toString());
                        favouriteSongs.add(f);
                        actualizarRecycler();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        return v;
    }

    private void actualizarRecycler(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        System.out.println("Tamaño lista ->>> "+favouriteSongs.size());
        FavouritesAdapter adapter = new FavouritesAdapter(favouriteSongs, getContext());
        recyclerView.setAdapter(adapter);
    }
}