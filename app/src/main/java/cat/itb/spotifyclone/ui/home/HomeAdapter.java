package cat.itb.spotifyclone.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cat.itb.spotifyclone.R;
import cat.itb.spotifyclone.model.Album;
import cat.itb.spotifyclone.model.AlbumSimple;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private List<Album> listaAlbumes;
    private Context context;

    public HomeAdapter(List<Album> listaAlbumes, Context context) {
        this.listaAlbumes = listaAlbumes;
        this.context = context;
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("album", album);
                    Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_fragmentAlbumList, bundle);
                }
            });
            titulo.setText(album.getTitle());
            Picasso.with(context).load(album.getCoverMedium()).into(imagen);
        }

    }
}

