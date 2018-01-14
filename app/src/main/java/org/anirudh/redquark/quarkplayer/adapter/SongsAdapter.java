package org.anirudh.redquark.quarkplayer.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.anirudh.redquark.quarkplayer.PlaySongActivity;
import org.anirudh.redquark.quarkplayer.R;
import org.anirudh.redquark.quarkplayer.model.Song;
import org.anirudh.redquark.quarkplayer.util.QuarkSharedPreferences;

import java.util.ArrayList;
import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.MyViewHolder> {

    private Context mContext;
    private List<Song> songsList;
    private List<Song> favoriteSongs = new ArrayList<>();
    private QuarkSharedPreferences sharedPreference;

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, artists;
        ImageView cover;

        MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.name);
            artists = view.findViewById(R.id.artist);
            cover = view.findViewById(R.id.cover);
        }
    }


    public SongsAdapter(Context mContext, List<Song> songsList) {
        this.mContext = mContext;
        this.songsList = songsList;
        sharedPreference = new QuarkSharedPreferences();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.song_card, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        final Song song = songsList.get(position);
        holder.name.setText(song.getSongName());
        holder.artists.setText(song.getArtists());
        Glide.with(mContext).load(song.getCoverUrl()).into(holder.cover);

        holder.cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PlaySongActivity.class);
                intent.putExtra("song", song);
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return songsList.size();
    }

    /* Checks whether a particular product exists in SharedPreferences */
    public boolean checkFavoriteItem(Song checkSong) {
        boolean check = false;
        List<Song> favorites = sharedPreference.getFavorites(mContext);
        if (favorites != null) {
            for (Song song : favorites) {
                if (song.equals(checkSong)) {
                    check = true;
                    break;
                }
            }
        }
        return check;
    }

    public void add(Song song) {
        favoriteSongs.add(song);
        notifyDataSetChanged();
    }

    public void remove(Song song) {
        favoriteSongs.remove(song);
        notifyDataSetChanged();
    }

}
