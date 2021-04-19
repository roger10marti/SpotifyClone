package cat.itb.spotifyclone.mediaplayer;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import java.io.IOException;

public class MusicPlayerService extends Service implements MediaPlayer.OnCompletionListener {

    MediaPlayer mediaPlayer;
    boolean isPlayerReady = false;
    String song;
    private final IBinder baBinder = new BackgroundAudioServiceBinder();

    @Override
    public void onCompletion(MediaPlayer mp) {
        stopSelf();
    }

    //  The first change that we'll need to make in our Service is to create an inner class that extends Binder that can return our Service itself when asked.

    public class BackgroundAudioServiceBinder extends Binder {
        MusicPlayerService getService() {
            return MusicPlayerService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return baBinder;
    }

    // Return the BackgroundAudioServiceBinder object return basBinder;
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        song = intent.getStringExtra("songUrl");
        mediaPlayer = new MediaPlayer();
        //To know media player is ready and music file loaded
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                isPlayerReady = true;
                Log.d("media_is_ready", "media_is_ready");
            }
        });

        //resetting MediaPlayer is always good.
        mediaPlayer.reset();


        try {

            mediaPlayer.setDataSource(song);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            Log.e("start_playing", "play: ", e);
        }


        return super.onStartCommand(intent, flags, startId);
    }

    public void play() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        } else {
            mediaPlayer.start();
        }
    }

    public void setSong() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
        mediaPlayer.release();
    }


}