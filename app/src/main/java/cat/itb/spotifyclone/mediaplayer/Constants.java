package cat.itb.spotifyclone.mediaplayer;

public class Constants {
    public interface ACTION {
        String MAIN_ACTION = "cat.itb.spotifyclone.mediaplayer.action.main";
        String PREV_ACTION = "cat.itb.spotifyclone.mediaplayer.action.prev";
        String PLAY_ACTION = "cat.itb.spotifyclone.mediaplayer.action.play";
        String NEXT_ACTION = "cat.itb.spotifyclone.mediaplayer.action.next";
        String STARTFOREGROUND_ACTION = "cat.itb.spotifyclone.mediaplayer.action.startforeground";
        String STOPFOREGROUND_ACTION = "cat.itb.spotifyclone.mediaplayer.action.stopforeground";
    }

    public interface NOTIFICATION_ID {
        int FOREGROUND_SERVICE = 101;
    }
}