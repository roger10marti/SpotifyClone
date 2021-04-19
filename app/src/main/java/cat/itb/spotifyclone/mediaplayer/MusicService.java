package cat.itb.spotifyclone.mediaplayer;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;


import java.io.IOException;
import java.util.ArrayList;

import cat.itb.spotifyclone.PlayerActivity;
import cat.itb.spotifyclone.model.Song;

public class MusicService extends Service implements MediaPlayer.OnPreparedListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnBufferingUpdateListener {

    //region "member variable"
    boolean isServiceRunning = false;
    ArrayList<Song> PlayerList = new ArrayList<>();
    MediaPlayer mediaPlayer;
    int position = 0;
    PlayerActivity mainActivity;
    private final IBinder mBinder = new LocalBinder();
    int playingMood;
    private final static int MAX_VOLUME = 15;
    Toast toast;
    public static MusicService objService;
    //endregion

    //region "service method"


    @Override
    public void onCreate() {
        objService = this;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        try {
            isServiceRunning = true;

            if (intent.getAction().equals(Constants.ACTION.STARTFOREGROUND_ACTION)) {
//                showNotification(false);
            } else if (intent.getAction().equals(Constants.ACTION.PREV_ACTION)) {
                playPrevious();
            } else if (intent.getAction().equals(Constants.ACTION.PLAY_ACTION)) {
                play();

            } else if (intent.getAction().equals(Constants.ACTION.NEXT_ACTION)) {
                playNext();
            } else if (intent.getAction().equals(
                    Constants.ACTION.STOPFOREGROUND_ACTION)) {
                stop();
                stopForeground(true);
                stopSelf();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        isServiceRunning = false;
        objService = null;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {

    }


    //returns the instance of the service
    public class LocalBinder extends Binder {
        public MusicService getServiceInstance() {
            return MusicService.this;
        }
    }

    public void registerClient(PlayerActivity activity) {
        mainActivity = activity;
    }
    //endregion


    //region "Media player"

    public void songRequest() {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }

            //Handel UI on main activity

            mediaPlayer = new MediaPlayer();

            prepareMediaPlayer(PlayerList.get(position).getPreview());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    @Override
    public void onPrepared(MediaPlayer mp) {
         try {
            mediaPlayer.start();
            mainActivity.checkPlaying(true);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        try {
            if (playingMood == 1) {
                mediaPlayer.start();
            }
            if (playingMood == 2) {
                playNext();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        try {
            Log.i("MediaPlayer", "error");

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }




    void startPrepare(String url) {

        prepareMediaPlayer(url);

    }



    void prepareMediaPlayer(String url) {
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnPreparedListener(MusicService.this);
            mediaPlayer.setOnErrorListener(MusicService.this);
            mediaPlayer.setOnCompletionListener(MusicService.this);
            mediaPlayer.setOnBufferingUpdateListener(MusicService.this);
            mediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //endregion

    //region "media player method"
    public boolean play() {
        if (mediaPlayer != null) {
            switchButton();
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();

                return false;
            } else {
                mediaPlayer.start();
                return true;
            }
        }

        return false;

    }

    void switchButton() {


    }

    public void stop() {
        try {
            if (mediaPlayer != null) {
                mediaPlayer.release();
                mediaPlayer = null;
            }
            isServiceRunning = false;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void playNext() {
        if (position < PlayerList.size() - 1) {
            position++;
            mediaPlayer.stop();
            songRequest();
        }
    }


    public void playPrevious() {
        if (position > 0) {
            position--;
            mediaPlayer.stop();
            songRequest();
        }
    }


    public void onError() {

    }


    public void onCompletion() {

    }


    public void onCleanMemory() {

    }


    public void initilizePlayerList(ArrayList<Song> list, int position) {
        this.PlayerList = list;
        this.position = position;
    }

    public boolean isplaying() {

        return mediaPlayer == null ? false : mediaPlayer.isPlaying();
    }

    public boolean isRunning() {

        return isServiceRunning;
    }

    public Song getCurrentSong() {
        if (PlayerList != null && PlayerList.size() != 0 && PlayerList.size() >= position) {
            return PlayerList.get(position);
        }
        return null;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    public void seekTo(int duration) {
        if (mediaPlayer != null) {
            mediaPlayer.seekTo(duration);
        }
    }

    public int getMood() {
        return playingMood;
    }

    public void setMood(int mood) {
        playingMood = mood;
    }

    public void setVolume(int soundVolume) {
        if (mediaPlayer != null) {
            final float volume = (float) (1 - (Math.log(MAX_VOLUME - soundVolume) / Math.log(MAX_VOLUME)));
            mediaPlayer.setVolume(volume, volume);
        }
    }

    //endregion

}