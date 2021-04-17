package cat.itb.spotifyclone.ui.library;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.itb.spotifyclone.R;
import cat.itb.spotifyclone.model.AlbumSimple;

public class LibraryPlaylistsAdapter extends RecyclerView.Adapter<LibraryPlaylistsAdapter.HomeViewHolder> {
    private List<AlbumSimple> listaAlbumes;

    public LibraryPlaylistsAdapter() {
        this.listaAlbumes = listaAlbumes;
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
        private TextView titulo;
        private ImageView imagen;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);/*
            titulo = itemView.findViewById(R.id.tituloText);
            imagen = itemView.findViewById(R.id.iconoText);*/

        }

        public void bind() {
            /*titulo.setText(album.getTitulo());
            imagen.setImageDrawable(ResourcesCompat.getDrawable(itemView.getResources(),album.getImagen(),null));*/
        }
    }
}
