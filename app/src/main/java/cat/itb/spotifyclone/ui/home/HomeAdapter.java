package cat.itb.spotifyclone.ui.home;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import cat.itb.spotifyclone.R;
import cat.itb.spotifyclone.model.Albumold;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private List<Albumold> listaAlbumes;
    private Context context;

    public HomeAdapter(List<Albumold> listaAlbumes, Context context) {
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
