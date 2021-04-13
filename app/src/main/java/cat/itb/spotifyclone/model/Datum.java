
package cat.itb.spotifyclone.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("readable")
    @Expose
    private boolean readable;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("title_short")
    @Expose
    private String titleShort;
    @SerializedName("title_version")
    @Expose
    private String titleVersion;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("duration")
    @Expose
    private int duration;
    @SerializedName("rank")
    @Expose
    private int rank;
    @SerializedName("explicit_lyrics")
    @Expose
    private boolean explicitLyrics;
    @SerializedName("explicit_content_lyrics")
    @Expose
    private int explicitContentLyrics;
    @SerializedName("explicit_content_cover")
    @Expose
    private int explicitContentCover;
    @SerializedName("preview")
    @Expose
    private String preview;
    @SerializedName("md5_image")
    @Expose
    private String md5Image;
    @SerializedName("artist")
    @Expose
    private Artist artist;
    @SerializedName("album")
    @Expose
    private Album album;
    @SerializedName("type")
    @Expose
    private String type;

    protected Datum(Parcel in) {
        id = in.readInt();
        readable = in.readByte() != 0;
        title = in.readString();
        titleShort = in.readString();
        titleVersion = in.readString();
        link = in.readString();
        duration = in.readInt();
        rank = in.readInt();
        explicitLyrics = in.readByte() != 0;
        explicitContentLyrics = in.readInt();
        explicitContentCover = in.readInt();
        preview = in.readString();
        md5Image = in.readString();
        type = in.readString();
    }

    public static final Creator<Datum> CREATOR = new Creator<Datum>() {
        @Override
        public Datum createFromParcel(Parcel in) {
            return new Datum(in);
        }

        @Override
        public Datum[] newArray(int size) {
            return new Datum[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isReadable() {
        return readable;
    }

    public void setReadable(boolean readable) {
        this.readable = readable;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleShort() {
        return titleShort;
    }

    public void setTitleShort(String titleShort) {
        this.titleShort = titleShort;
    }

    public String getTitleVersion() {
        return titleVersion;
    }

    public void setTitleVersion(String titleVersion) {
        this.titleVersion = titleVersion;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isExplicitLyrics() {
        return explicitLyrics;
    }

    public void setExplicitLyrics(boolean explicitLyrics) {
        this.explicitLyrics = explicitLyrics;
    }

    public int getExplicitContentLyrics() {
        return explicitContentLyrics;
    }

    public void setExplicitContentLyrics(int explicitContentLyrics) {
        this.explicitContentLyrics = explicitContentLyrics;
    }

    public int getExplicitContentCover() {
        return explicitContentCover;
    }

    public void setExplicitContentCover(int explicitContentCover) {
        this.explicitContentCover = explicitContentCover;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getMd5Image() {
        return md5Image;
    }

    public void setMd5Image(String md5Image) {
        this.md5Image = md5Image;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeByte((byte) (readable ? 1 : 0));
        dest.writeString(title);
        dest.writeString(titleShort);
        dest.writeString(titleVersion);
        dest.writeString(link);
        dest.writeInt(duration);
        dest.writeInt(rank);
        dest.writeByte((byte) (explicitLyrics ? 1 : 0));
        dest.writeInt(explicitContentLyrics);
        dest.writeInt(explicitContentCover);
        dest.writeString(preview);
        dest.writeString(md5Image);
        dest.writeString(type);
    }
}
