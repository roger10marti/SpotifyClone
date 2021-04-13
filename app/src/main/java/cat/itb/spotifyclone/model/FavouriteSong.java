package cat.itb.spotifyclone.model;

public class FavouriteSong {
    private String idFavourite;
    private String song;
    private String fecha;

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
}
