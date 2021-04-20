package cat.itb.spotifyclone.ui.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import cat.itb.spotifyclone.R;
import cat.itb.spotifyclone.model.AlbumSimple;
import cat.itb.spotifyclone.model.FavouriteSong;

public class LibraryPlaylistsAdapter extends RecyclerView.Adapter<LibraryPlaylistsAdapter.HomeViewHolder> {
    private List<AlbumSimple> listaAlbumes;
    private long numeroCancionesFirebase;

    public LibraryPlaylistsAdapter(long numeroCancionesFirebase) {
        this.listaAlbumes = listaAlbumes;
        this.numeroCancionesFirebase = numeroCancionesFirebase;
    }

    @NonNull
    @Override
    public LibraryPlaylistsAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_playlists,parent,false);
        return new LibraryPlaylistsAdapter.HomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryPlaylistsAdapter.HomeViewHolder holder, int position) {
        holder.bind();
    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo, numeroCanciones;
        private ImageView imagen;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);/*
            titulo = itemView.findViewById(R.id.tituloText);
            imagen = itemView.findViewById(R.id.iconoText);*/
            numeroCanciones = itemView.findViewById(R.id.numeroCanciones);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Navigation.findNavController(v).navigate(R.id.action_basePageLibrary2_to_listFavouriteSongs);
                }
            });

        }

        public void bind() {
            /*titulo.setText(album.getTitulo());
            imagen.setImageDrawable(ResourcesCompat.getDrawable(itemView.getResources(),album.getImagen(),null));*/
            numeroCanciones.setText("Numero de canciones favoritas: "+String.valueOf(numeroCancionesFirebase));

        }
    }


}