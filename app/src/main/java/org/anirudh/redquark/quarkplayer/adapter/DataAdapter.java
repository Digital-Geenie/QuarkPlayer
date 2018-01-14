package org.anirudh.redquark.quarkplayer.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import org.anirudh.redquark.quarkplayer.R;
import org.anirudh.redquark.quarkplayer.model.Song;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements Filterable {

    private ArrayList<Song> songList;
    private ArrayList<Song> filteredSongList;
    private Context context;

    public DataAdapter(ArrayList<Song> list, Context context){
        songList = list;
        filteredSongList = list;
        this.context = context;
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.name.setText(filteredSongList.get(i).getSongName());
        viewHolder.artist.setText(filteredSongList.get(i).getArtists());
        Glide.with(context).load(filteredSongList.get(i).getCoverUrl()).into(viewHolder.cover);
    }

    @Override
    public int getItemCount() {
        return filteredSongList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String charString = charSequence.toString();

                if (charString.isEmpty()) {

                    filteredSongList = songList;
                } else {

                    ArrayList<Song> filteredList = new ArrayList<>();

                    for (Song song : songList) {

                        if (song.getArtists().toLowerCase().contains(charString) || song.getSongName().toLowerCase().contains(charString)) {

                            filteredList.add(song);
                        }
                    }

                    filteredSongList = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredSongList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredSongList = (ArrayList<Song>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.song_card, viewGroup, false);
        return new ViewHolder(view);
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView name, artist;
        private ImageView cover;
        ViewHolder(View view) {
            super(view);

            name = view.findViewById(R.id.name);
            artist = view.findViewById(R.id.artist);
            cover = view.findViewById(R.id.cover);

        }
    }
}
