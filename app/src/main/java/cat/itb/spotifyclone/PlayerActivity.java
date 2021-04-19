package cat.itb.spotifyclone;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
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
import java.util.ArrayList;

import cat.itb.spotifyclone.api.ApiHelper;
import cat.itb.spotifyclone.mediaplayer.Constants;
import cat.itb.spotifyclone.mediaplayer.MusicPlayerService;
import cat.itb.spotifyclone.mediaplayer.MusicService;
import cat.itb.spotifyclone.model.Album;
import cat.itb.spotifyclone.model.FavouriteSong;
import cat.itb.spotifyclone.model.Song;


public class PlayerActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView b_play, b_back, b_forward, b_backwards, cover, favImageView;
    private TextView songTitleText, songArtistText, duration;
    private SeekBar timer;

    boolean favourite = false;
    private DatabaseReference dref;
    FirebaseDatabase firebaseDatabase;
    public static String key;

    private Thread t;
    private Album album;
    boolean playing = false;
    private MusicService service;
    private ArrayList<Song> playlist;

    //Per a obtenir instancia del servei
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder _service) {
            MusicService.LocalBinder binder = (MusicService.LocalBinder) _service;
            service = binder.getServiceInstance(); //Get instance of your service!
            service.registerClient(PlayerActivity.this); //Activity register in the service as client for callabcks!
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        //Findviews i onclicks
        favImageView = findViewById(R.id.imageViewCorazon);
        favImageView.setOnClickListener(this);
        songTitleText = findViewById(R.id.songtitle);
        b_forward = findViewById(R.id.forwards);
        b_forward.setOnClickListener(this);
        b_backwards = findViewById(R.id.backwards);
        b_backwards.setOnClickListener(this);
        duration = findViewById(R.id.duration);
        timer = findViewById(R.id.timer);
        cover = findViewById(R.id.song_img);
        songArtistText = findViewById(R.id.artisttext);
        b_back = findViewById(R.id.arrdown);
        b_back.setOnClickListener(this);
        b_play = findViewById(R.id.b_play);
        b_play.setOnClickListener(this);


        //Firebase favorits
        firebaseDatabase = FirebaseDatabase.getInstance();
        dref = firebaseDatabase.getReference("Favourites");
        checkFav();

        //Per a activar el marques del text
        songTitleText.setSelected(true);

        timer.setMax(30);
        service = new MusicService();

        // S'ha de tornar a cridar la Api perque no es passen els Tracks
        Bundle b = getIntent().getExtras();
        if (b != null) {
            int id = b.getInt("id");
            int pos = b.getInt("pos");
            album = ApiHelper.consultarAlbum(id);
            playlist = (ArrayList<Song>) album.getTracks().getData();
            startMusicService();
            service.initilizePlayerList(playlist, pos);
            service.songRequest();
            service.play();
        }

        t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (timer.getProgress() < 30) {
                    timer.setProgress(timer.getProgress() + 1);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();

        updateUi();
    }

    private void checkFav() {
        Query q = dref.orderByChild("song").equalTo(songTitleText.getText().toString());
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot post : snapshot.getChildren()) {
                    if (post.exists()) {
                        key = (String) post.child("idFavourite").getValue();
                        favImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_action_favorite));
                        Toast.makeText(PlayerActivity.this, "Añadida a favoritos", Toast.LENGTH_SHORT).show();
                        favourite = true;
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void startMusicService() {
        Intent serviceIntent = new Intent(this, MusicService.class);
        serviceIntent.setAction(Constants.ACTION.PLAY_ACTION);
        startService(serviceIntent);
        bindService(serviceIntent, mConnection, Context.BIND_AUTO_CREATE);
    }

    public void stopMusicService() {
        if (service != null) {
            try {
                service.stop();
                unbindService(mConnection);
                stopService(new Intent(this, service.getClass()));
                service = null;
            } catch (IllegalArgumentException ex) {
                stopService(new Intent(this, service.getClass()));
                service = null;
                ex.printStackTrace();
            }
        }
    }

    private void setPlayButtonImg() {
        if (service.isplaying()) {
            b_play.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_pause));
        } else {
            b_play.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_play));
        }
    }

    public void checkPlaying(boolean b) {
        playing = b;
    }

    private void updateUi() {
        timer.setProgress(2);
        songTitleText.setText(service.getCurrentSong().getTitle());
        songArtistText.setText(album.getArtist().getName());
        int durationInt = service.getCurrentSong().getDuration();
        int minuts = durationInt / 60;
        int segons = durationInt % 60;
        duration.setText(minuts + ":" + segons);
        Picasso.with(getApplicationContext()).load(album.getCoverXl()).into(cover);
    }

    private void actualizarFav() {
        if (!favourite) {
            favImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_action_favorite));
            favourite = true;
            FavouriteSong fav = new FavouriteSong();
            fav.setSong(songTitleText.getText().toString());
            String key = dref.push().getKey();
            fav.setIdFavourite(key);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                fav.setFecha(LocalDate.now().toString());
            }
            dref.child(key).setValue(fav);

        } else {
            favImageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.ic_action_favourite));
            favourite = false;
            System.out.println("Key ->>>>>>>>>><" + key);
            dref.child(key).removeValue();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.forwards:
                service.playNext();
                updateUi();
                break;
            case R.id.backwards:
                service.playPrevious();
                updateUi();
                break;
            case R.id.b_play:
                service.play();
                setPlayButtonImg();
                updateUi();
                break;
            case R.id.imageViewCorazon:
                actualizarFav();
                break;
            case R.id.arrdown:
                stopMusicService();
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        stopMusicService();
        finish();
    }
}