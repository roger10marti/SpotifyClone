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
import cat.itb.spotifyclone.model.Song;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.SongsViewHolder> {
    private List<Song> listasongs;

    public SongsAdapter(List<Song> listasongs) {
        this.listasongs = listasongs;
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

    public static class SongsViewHolder extends RecyclerView.ViewHolder {
        private TextView titulo;
        private TextView album;

        public SongsViewHolder(@NonNull final View itemView) {
            super(itemView);
            titulo = itemView.findViewById(R.id.textTitle);
            album = itemView.findViewById(R.id.textArtist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PlayerActivity.class);
                    v.getContext().startActivity(intent);
                }
            });

        }

        public void bind(Song song) {
            titulo.setText(song.getTitle());
            album.setText(song.getAlbum());
        }
    }
}
