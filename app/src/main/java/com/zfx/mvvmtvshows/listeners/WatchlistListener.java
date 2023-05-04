package com.zfx.mvvmtvshows.listeners;

import com.zfx.mvvmtvshows.models.TVShow;

public interface WatchlistListener {
    void onTVShowClicked(TVShow tvShow);
    void removeTVShowFromWatchlist(TVShow tvShow , int position);
}
