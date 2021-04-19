package cat.itb.spotifyclone.model;

public class FavouriteSong {
    private String idFavourite;
    private String song;
    private String fecha;
    private String artist;
    private String preview;
    private String cover;
    private int duration;

    public FavouriteSong() {
    }

    public FavouriteSong(String idFavourite, String song, String fecha) {
        this.idFavourite = idFavourite;
        this.song = song;
        this.fecha = fecha;
    }

    public String getIdFavourite() {
        return idFavourite;
    }

    public void setIdFavourite(String idFavourite) {
        this.idFavourite = idFavourite;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
