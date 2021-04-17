package cat.itb.spotifyclone.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import cat.itb.spotifyclone.model.Contributor;
import cat.itb.spotifyclone.model.Tracks;

public class Album implements Parcelable {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("upc")
    @Expose
    private String upc;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("share")
    @Expose
    private String share;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("cover_small")
    @Expose
    private String coverSmall;
    @SerializedName("cover_medium")
    @Expose
    private String coverMedium;
    @SerializedName("cover_big")
    @Expose
    private String coverBig;
    @SerializedName("cover_xl")
    @Expose
    private String coverXl;
    @SerializedName("md5_image")
    @Expose
    private String md5Image;
    @SerializedName("genre_id")
    @Expose
    private int genreId;
    @SerializedName("genres")
    @Expose
    private Genres genres;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("nb_tracks")
    @Expose
    private int nbTracks;
    @SerializedName("duration")
    @Expose
    private int duration;
    @SerializedName("fans")
    @Expose
    private int fans;
    @SerializedName("rating")
    @Expose
    private int rating;
    @SerializedName("release_date")
    @Expose
    private String releaseDate;
    @SerializedName("record_type")
    @Expose
    private String recordType;
    @SerializedName("available")
    @Expose
    private boolean available;
    @SerializedName("tracklist")
    @Expose
    private String tracklist;
    @SerializedName("explicit_lyrics")
    @Expose
    private boolean explicitLyrics;
    @SerializedName("explicit_content_lyrics")
    @Expose
    private int explicitContentLyrics;
    @SerializedName("explicit_content_cover")
    @Expose
    private int explicitContentCover;
    @SerializedName("contributors")
    @Expose
    private List<Contributor> contributors = null;
    @SerializedName("artist")
    @Expose
    private Artist artist;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("tracks")
    @Expose
    private Tracks tracks;

    protected Album(Parcel in) {
        id = in.readInt();
        title = in.readString();
        upc = in.readString();
        link = in.readString();
        share = in.readString();
        cover = in.readString();
        coverSmall = in.readString();
        coverMedium = in.readString();
        coverBig = in.readString();
        coverXl = in.readString();
        md5Image = in.readString();
        genreId = in.readInt();
        label = in.readString();
        nbTracks = in.readInt();
        duration = in.readInt();
        fans = in.readInt();
        rating = in.readInt();
        releaseDate = in.readString();
        recordType = in.readString();
        available = in.readByte() != 0;
        tracklist = in.readString();
        explicitLyrics = in.readByte() != 0;
        explicitContentLyrics = in.readInt();
        explicitContentCover = in.readInt();
        type = in.readString();
    }

    public static final Creator<Album> CREATOR = new Creator<Album>() {
        @Override
        public Album createFromParcel(Parcel in) {
            return new Album(in);
        }

        @Override
        public Album[] newArray(int size) {
            return new Album[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUpc() {
        return upc;
    }

    public void setUpc(String upc) {
        this.upc = upc;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCoverSmall() {
        return coverSmall;
    }

    public void setCoverSmall(String coverSmall) {
        this.coverSmall = coverSmall;
    }

    public String getCoverMedium() {
        return coverMedium;
    }

    public void setCoverMedium(String coverMedium) {
        this.coverMedium = coverMedium;
    }

    public String getCoverBig() {
        return coverBig;
    }

    public void setCoverBig(String coverBig) {
        this.coverBig = coverBig;
    }

    public String getCoverXl() {
        return coverXl;
    }

    public void setCoverXl(String coverXl) {
        this.coverXl = coverXl;
    }

    public String getMd5Image() {
        return md5Image;
    }

    public void setMd5Image(String md5Image) {
        this.md5Image = md5Image;
    }

    public int getGenreId() {
        return genreId;
    }

    public void setGenreId(int genreId) {
        this.genreId = genreId;
    }

    public Genres getGenres() {
        return genres;
    }

    public void setGenres(Genres genres) {
        this.genres = genres;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public int getNbTracks() {
        return nbTracks;
    }

    public void setNbTracks(int nbTracks) {
        this.nbTracks = nbTracks;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getFans() {
        return fans;
    }

    public void setFans(int fans) {
        this.fans = fans;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getTracklist() {
        return tracklist;
    }

    public void setTracklist(String tracklist) {
        this.tracklist = tracklist;
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

    public List<Contributor> getContributors() {
        return contributors;
    }

    public void setContributors(List<Contributor> contributors) {
        this.contributors = contributors;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Tracks getTracks() {
        return tracks;
    }

    public void setTracks(Tracks tracks) {
        this.tracks = tracks;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(upc);
        dest.writeString(link);
        dest.writeString(share);
        dest.writeString(cover);
        dest.writeString(coverSmall);
        dest.writeString(coverMedium);
        dest.writeString(coverBig);
        dest.writeString(coverXl);
        dest.writeString(md5Image);
        dest.writeInt(genreId);
        dest.writeString(label);
        dest.writeInt(nbTracks);
        dest.writeInt(duration);
        dest.writeInt(fans);
        dest.writeInt(rating);
        dest.writeString(releaseDate);
        dest.writeString(recordType);
        dest.writeByte((byte) (available ? 1 : 0));
        dest.writeString(tracklist);
        dest.writeByte((byte) (explicitLyrics ? 1 : 0));
        dest.writeInt(explicitContentLyrics);
        dest.writeInt(explicitContentCover);
        dest.writeString(type);
    }
}
