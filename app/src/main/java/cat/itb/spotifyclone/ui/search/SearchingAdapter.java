package cat.itb.spotifyclone.ui.search;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cat.itb.spotifyclone.PlayerActivity;
import cat.itb.spotifyclone.R;
import cat.itb.spotifyclone.api.ApiHelper;
import cat.itb.spotifyclone.model.Album;
import cat.itb.spotifyclone.model.Datum;
import cat.itb.spotifyclone.model.Song;


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
            album.setText(busqueda.getAlbumSimple().getTitle());
            Picasso.with(context).load(busqueda.getAlbumSimple().getCoverMedium()).into(imagen);

            //Recupera el album original per a passar al player la posicio
            Album album = ApiHelper.consultarAlbum(busqueda.getAlbumSimple().getId());
            List<Song> songs = album.getTracks().getData();

            int pos = 0;
            //Comprova la posicio
            for (int i = 0; i < songs.size(); i++) {
                if (songs.get(i).getTitle().equals(busqueda.getTitle())){
                    pos = i;
                }
            }
            int finalPos = pos;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PlayerActivity.class);
                    intent.putExtra("id",album.getId());
                    intent.putExtra("pos", finalPos);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}

