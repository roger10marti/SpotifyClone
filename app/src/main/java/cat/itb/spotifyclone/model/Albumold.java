package cat.itb.spotifyclone.model;

import android.graphics.drawable.Drawable;
import android.media.Image;

public class Albumold {
    private String titulo;
    private int imagen;

    public Albumold(String titulo, int imagen) {
        this.titulo = titulo;
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }
}
