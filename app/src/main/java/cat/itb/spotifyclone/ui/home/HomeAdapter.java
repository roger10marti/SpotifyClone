package cat.itb.spotifyclone.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.itb.spotifyclone.R;
import cat.itb.spotifyclone.model.Album;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private List<Album> listaAlbumes;

    public HomeAdapter(List<Album> listaAlbumes) {
        this.listaAlbumes = listaAlbumes;
    }

    @NonNull
    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_item,parent,false);
        return new HomeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeViewHolder holder, int position) {
        holder.bind(listaAlbumes.get(position));
    }

    @Override
    public int getItemCount() {
        return listaAlbumes.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo;
        private ImageView imagen;

        public HomeViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tituloText);
            imagen = itemView.findViewById(R.id.iconoText);

        }

        public void bind(Album album) {
            titulo.setText(album.getTitulo());
            imagen.setImageDrawable(ResourcesCompat.getDrawable(itemView.getResources(),album.getImagen(),null));
        }
    }
}
