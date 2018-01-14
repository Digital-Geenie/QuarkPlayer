package org.anirudh.redquark.quarkplayer.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Model class for getting song details
 */

public class Song implements Parcelable {

    private String songName;
    private String songUrl;
    private String artists;
    private String coverUrl;

    private Song(Parcel in){
        songName = in.readString();
        songUrl = in.readString();
        artists = in.readString();
        coverUrl = in.readString();
    }

    public Song(){

    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getSongUrl() {
        return songUrl;
    }

    public void setSongUrl(String songUrl) {
        this.songUrl = songUrl;
    }

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){

        dest.writeString(songName);
        dest.writeString(songUrl);
        dest.writeString(artists);
        dest.writeString(coverUrl);
    }

    public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>(){

        public Song createFromParcel(Parcel in){
            return new Song(in);
        }

        @Override
        public Song[] newArray(int i) {
            return new Song[i];
        }
    };
}
