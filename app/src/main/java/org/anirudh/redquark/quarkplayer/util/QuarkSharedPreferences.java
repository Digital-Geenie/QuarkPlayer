package org.anirudh.redquark.quarkplayer.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import org.anirudh.redquark.quarkplayer.model.Song;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuarkSharedPreferences {

    private static final String PREFS_NAME = "OLA_APP";
    private static final String FAVORITES = "Ola_Favorite";

    // This four methods are used for maintaining favorites.
    public void saveFavorites(Context context, List<Song> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(FAVORITES, jsonFavorites);

        editor.apply();
    }

    public void addFavorite(Context context, Song song) {
        List<Song> favorites = getFavorites(context);
        if (favorites == null)
            favorites = new ArrayList<>();
        favorites.add(song);
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, Song song) {
        ArrayList<Song> favorites = getFavorites(context);
        if (favorites != null) {
            favorites.remove(song);
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<Song> getFavorites(Context context) {
        SharedPreferences settings;
        List<Song> favorites;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);

        if (settings.contains(FAVORITES)) {
            String jsonFavorites = settings.getString(FAVORITES, null);
            Gson gson = new Gson();
            Song[] favoriteItems = gson.fromJson(jsonFavorites,
                    Song[].class);

            favorites = Arrays.asList(favoriteItems);
            favorites = new ArrayList<>(favorites);
        } else
            return null;

        return (ArrayList<Song>) favorites;
    }
}
