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
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.material.slider.Slider;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import cat.itb.spotifyclone.model.Datum;

public class PlayerActivity extends AppCompatActivity {

    private ImageView b_play, b_back, cover;
    private TextView songTitleText, songArtistText, duration;
    private SeekBar timer;
    private boolean playing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        songTitleText = findViewById(R.id.songtitle);
        songTitleText.setSelected(true);
        duration = findViewById(R.id.duration);
        timer = findViewById(R.id.timer);
        timer.setMax(30);


        cover = findViewById(R.id.song_img);
        songArtistText = findViewById(R.id.artisttext);
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
            songTitleText.setText(b.getString("titulo"));
            songArtistText.setText(b.getString("artista"));
            int durationInt = b.getInt("duration");
            int minuts = durationInt/60;
            int segons = durationInt%60;
            duration.setText(minuts+":"+segons);
            Picasso.with(getApplicationContext()).load(b.getString("cover")).into(cover);
            MediaPlayer mediaPlayer = new MediaPlayer();
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (timer.getProgress()<30) {
                        timer.setProgress(timer.getProgress() + 1);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    mediaPlayer.setAudioAttributes(
                            new AudioAttributes.Builder()
                                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                    .setUsage(AudioAttributes.USAGE_MEDIA)
                                    .build()
                    );
                }
                mediaPlayer.setDataSource(b.getString("preview"));
                mediaPlayer.prepare(); // might take long! (for buffering, etc)
                mediaPlayer.start();
                t.start();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}