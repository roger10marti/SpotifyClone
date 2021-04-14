package cat.itb.spotifyclone.ui.search;

import android.content.Context;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Build;
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

import java.io.IOException;
import java.util.List;

import cat.itb.spotifyclone.PlayerActivity;
import cat.itb.spotifyclone.R;
import cat.itb.spotifyclone.model.Datum;



public class SearchingAdapter extends RecyclerView.Adapter<SearchingAdapter.SearchingViewHolder> {
    private List<Datum> listaBusqueda;
    private Context context;

    public SearchingAdapter(List<Datum> listaBusqueda, Context context) {
        this.listaBusqueda = listaBusqueda;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchingAdapter.SearchingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search,parent,false);
        return new SearchingViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchingAdapter.SearchingViewHolder holder, int position) {
        holder.bind(listaBusqueda.get(position));
    }

    @Override
    public int getItemCount() {
        return listaBusqueda.size();
    }

    public class SearchingViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo, album;
        private ImageView imagen;

        public SearchingViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.search_title);
            album = itemView.findViewById(R.id.search_album);
            imagen = itemView.findViewById(R.id.search_img);



        }

        public void bind(Datum busqueda) {
            titulo.setText(busqueda.getTitle());
            album.setText(busqueda.getAlbum().getTitle());
            Picasso.with(context).load(busqueda.getAlbum().getCoverSmall()).into(imagen);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*
                    Bundle bundle = new Bundle();
                    bundle.putString("title", titulo.getText().toString());
                    Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_fragmentAlbumList, bundle);*/

                    Intent intent = new Intent(v.getContext(), PlayerActivity.class);
                    intent.putExtra("titulo", busqueda.getTitle());
                    intent.putExtra("artista", busqueda.getArtist().getName());
                    intent.putExtra("cover", busqueda.getAlbum().getCoverBig());
                    intent.putExtra("preview", busqueda.getPreview());
                    intent.putExtra("duration", busqueda.getDuration());
                    v.getContext().startActivity(intent);
                }
            });

        }

    }
}

