package org.anirudh.redquark.quarkplayer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.anirudh.redquark.quarkplayer.adapter.SongsAdapter;
import org.anirudh.redquark.quarkplayer.model.Song;
import org.anirudh.redquark.quarkplayer.util.QuarkSharedPreferences;

import java.util.List;

public class PlaylistActivity extends AppCompatActivity {

    private List<Song> favoriteList;
    private QuarkSharedPreferences olaSharedPreference;
    private SongsAdapter songsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
        Button removeAll = findViewById(R.id.remove_all);

        olaSharedPreference = new QuarkSharedPreferences();
        favoriteList = olaSharedPreference.getFavorites(this);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(songsAdapter);

        if (favoriteList == null) {
            showAlert(getResources().getString(R.string.no_favorites_items),
                    getResources().getString(R.string.no_favorites_msg));
        }else {

            if (favoriteList.size() == 0) {
                showAlert(
                        getResources().getString(R.string.no_favorites_items),
                        getResources().getString(R.string.no_favorites_msg));
            }
        }

        if(favoriteList!=null){
            songsAdapter = new SongsAdapter(this, favoriteList);
            recyclerView.setAdapter(songsAdapter);
        }

        removeAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favoriteList.clear();
                olaSharedPreference.saveFavorites(PlaylistActivity.this, favoriteList);
                Toast.makeText(PlaylistActivity.this, "Playlist is cleared", Toast.LENGTH_LONG).show();
                startActivity(new Intent(PlaylistActivity.this, SongListActivity.class));
            }
        });
    }
    public void showAlert(String title, String message) {
        AlertDialog alertDialog = new AlertDialog.Builder(this).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setCancelable(false);

        // setting OK Button
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK",
                new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        startActivity(new Intent(PlaylistActivity.this, SongListActivity.class));
                        // activity.finish();
                        getFragmentManager().popBackStackImmediate();
                    }
                });
        alertDialog.show();
        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE)
                .setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

}
