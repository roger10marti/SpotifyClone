package cat.itb.spotifyclone.ui.library;

import android.content.Context;
import android.content.Intent;
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

import cat.itb.spotifyclone.PlayerActivity;
import cat.itb.spotifyclone.R;
import cat.itb.spotifyclone.model.FavouriteSong;


public class FavouritesAdapter extends RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder> {
    private List<FavouriteSong> listaFavoritos;
    private Context context;

    public FavouritesAdapter(List<FavouriteSong> listaFavoritos, Context context) {
        this.listaFavoritos = listaFavoritos;
        this.context = context;
    }

    @NonNull
    @Override
    public FavouritesAdapter.FavouritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_fav,parent,false);
        return new FavouritesViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouritesAdapter.FavouritesViewHolder holder, int position) {
        holder.bind(listaFavoritos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaFavoritos.size();
    }

    public class FavouritesViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo, artist, date;
        private ImageView imagen;

        public FavouritesViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textTitulo);
            artist = itemView.findViewById(R.id.textArtista);
            date = itemView.findViewById(R.id.textFecha);
            imagen = itemView.findViewById(R.id.imageView);

        }

        public void bind(FavouriteSong fav) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PlayerActivity.class);
                    intent.putExtra("id", fav.getIdAlbum());
                    System.out.println("ID ALBUM ANTES INTENT"+fav.getIdAlbum());
                    intent.putExtra("pos", fav.getPosAlbum());
                    context.startActivity(intent);
                }
            });
            titulo.setText(fav.getSong());
            artist.setText(fav.getArtist());
            date.setText(fav.getFecha());
            Picasso.with(context).load(fav.getCover()).into(imagen);
        }

    }
}

