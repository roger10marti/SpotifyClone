package cat.itb.spotifyclone.ui.search;

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
import cat.itb.spotifyclone.model.Albumold;


public class SearchingAdapter extends RecyclerView.Adapter<SearchingAdapter.SearchingViewHolder> {
    private List<Albumold> listaAlbumes;
    private Context context;

    public SearchingAdapter(List<Albumold> listaAlbumes, Context context) {
        this.listaAlbumes = listaAlbumes;
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
        holder.bind(listaAlbumes.get(position));
    }

    @Override
    public int getItemCount() {
        return listaAlbumes.size();
    }

    public class SearchingViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo;
        private ImageView imagen;

        public SearchingViewHolder(@NonNull View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.tituloText);
            imagen = itemView.findViewById(R.id.iconoText);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Bundle bundle = new Bundle();
                    bundle.putString("title", titulo.getText().toString());
                    Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_fragmentAlbumList, bundle);
                }
            });

        }

        public void bind(Albumold albumold) {
            titulo.setText(albumold.getTitulo());
            Picasso.with(context).load(albumold.getImagen()).into(imagen);
        }

    }
}

