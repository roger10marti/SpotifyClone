package cat.itb.spotifyclone.ui.secondaryfragments;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cat.itb.spotifyclone.PlayerActivity;
import cat.itb.spotifyclone.R;
import cat.itb.spotifyclone.model.Album;
import cat.itb.spotifyclone.model.Song;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongsViewHolder> {
    private List<Song> listasongs;
    private Album album;   //Necessari perque Song no conté info sobre l'album

    public SongsAdapter(List<Song> listasongs, Album album) {
        this.listasongs = listasongs;
        this.album = album;
    }

    @NonNull
    @Override
    public SongsAdapter.SongsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_song,parent,false);

        return new SongsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SongsAdapter.SongsViewHolder holder, int position) {
        holder.bind(listasongs.get(position));
    }

    @Override
    public int getItemCount() {
        return listasongs.size();
    }

    public class SongsViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo;
        private TextView artist;

        public SongsViewHolder(@NonNull final View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textTitle);
            artist = itemView.findViewById(R.id.textArtist);
        }

        public void bind(Song song) {

            titulo.setText(song.getTitle());
            artist.setText(song.getArtist().getName());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PlayerActivity.class);
                    intent.putExtra("id",album.getId());
                    intent.putExtra("pos",getAdapterPosition());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
