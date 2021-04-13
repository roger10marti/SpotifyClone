package cat.itb.spotifyclone;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import cat.itb.spotifyclone.model.Datum;

public class PlayerActivity extends AppCompatActivity {

    private ImageView b_play, b_back, cover;
    private TextView songTitleText, songAlbumText;
    private boolean playing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        songTitleText = findViewById(R.id.songtitle);
        songTitleText.setSelected(true);

        cover = findViewById(R.id.song_img);
        songAlbumText = findViewById(R.id.albumtext);
        b_back = findViewById(R.id.arrdown);
        b_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        b_play = findViewById(R.id.b_play);
        b_play.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                if (playing){
                    b_play.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_pause));
                    playing=false;
                }else {
                    b_play.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_play));
                    playing=true;
                }
            }
        });

        Bundle b = getIntent().getExtras();
        if (b!=null) {
            Datum cancion = b.getParcelable("cancion");

            songTitleText.setText(cancion.getTitle());
            songAlbumText.setText(cancion.getAlbum().getTitle());
            Picasso.with(getApplicationContext()).load(cancion.getAlbum().getCover()).into(cover);
            MediaPlayer mediaPlayer = new MediaPlayer();
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mediaPlayer.setAudioAttributes(
                            new AudioAttributes.Builder()
                                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                    .setUsage(AudioAttributes.USAGE_MEDIA)
                                    .build()
                    );
                }
                mediaPlayer.setDataSource(cancion.getPreview());
                mediaPlayer.prepare(); // might take long! (for buffering, etc)
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}