package cat.itb.spotifyclone;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.time.LocalDate;

import cat.itb.spotifyclone.model.FavouriteSong;

public class PlayerActivity extends AppCompatActivity {

    private ImageView b_play, b_back, cover, favImageView;
    private TextView songTitleText, songArtistText, duration;
    private SeekBar timer;
    private MediaPlayer mediaPlayer;
    boolean favourite = false;
    private DatabaseReference dref;
    FirebaseDatabase firebaseDatabase;
    public static String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        Bundle b = getIntent().getExtras();

        firebaseDatabase = FirebaseDatabase.getInstance();
        dref = firebaseDatabase.getReference("Favourites");
        favImageView = findViewById(R.id.imageViewCorazon);
        songTitleText = findViewById(R.id.songtitle);
        songTitleText.setSelected(true);

        duration = findViewById(R.id.duration);
        timer = findViewById(R.id.timer);
        timer.setMax(30);

        Query q = dref.orderByChild("song").equalTo(songTitleText.getText().toString());
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot post : snapshot.getChildren()) {
                    if (post.exists()) {
                        key = (String) post.child("idFavourite").getValue();
                        favImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_action_favorite));
                        Toast.makeText(PlayerActivity.this, "Añadida a favoritos", Toast.LENGTH_SHORT).show();
                        favourite = true;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        favImageView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (!favourite) {
                    favImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_action_favorite));
                    favourite = true;
                    FavouriteSong fav = new FavouriteSong();
                    fav.setSong(songTitleText.getText().toString());
                    String key = dref.push().getKey();
                    fav.setIdFavourite(key);
                    fav.setFecha(LocalDate.now().toString());
                    fav.setArtist(songArtistText.getText().toString());
                    fav.setCover(b.getString("cover"));
                    fav.setPreview(b.getString("preview"));
                    fav.setDuration(b.getInt("duration"));
                    dref.child(key).setValue(fav);

                } else {
                    favImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_action_favourite));
                    favourite = false;
                    System.out.println("Key ->>>>>>>>>><"+ key);
                    dref.child(key).removeValue();
                }
            }
        });
        mediaPlayer = new MediaPlayer();
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
                switchPlay();
                setPlayButtonImg();
            }
        });

        if (b!=null) {
            songTitleText.setText(b.getString("titulo"));
            songArtistText.setText(b.getString("artista"));
            int durationInt = b.getInt("duration");
            int minuts = durationInt/60;
            int segons = durationInt%60;
            duration.setText(minuts+":"+segons);
            Picasso.with(getApplicationContext()).load(b.getString("cover")).into(cover);

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
                setPlayButtonImg();
                t.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPlayButtonImg(){
        if (mediaPlayer.isPlaying()){
            b_play.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_pause));
        }else {
            b_play.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),R.drawable.ic_play));
        }
    }

    private void switchPlay(){
        if (mediaPlayer.isPlaying()){
            mediaPlayer.pause();
        }else {
            mediaPlayer.start();
        }
    }


}