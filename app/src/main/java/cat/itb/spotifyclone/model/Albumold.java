package cat.itb.spotifyclone.model;

import android.graphics.drawable.Drawable;
import android.media.Image;

public class Albumold {
    private String titulo;
    private String imagen;

    public Albumold(String titulo, String imagen) {
        this.titulo = titulo;
        this.imagen = imagen;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
}
