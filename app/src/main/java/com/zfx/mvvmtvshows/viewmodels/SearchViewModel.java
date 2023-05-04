package com.zfx.mvvmtvshows.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.zfx.mvvmtvshows.repositories.SearchTVShowRepository;
import com.zfx.mvvmtvshows.responses.TVShowsResponse;

public class SearchViewModel extends ViewModel {
    private SearchTVShowRepository searchTVShowRepository;

    public SearchViewModel() {
        searchTVShowRepository = new SearchTVShowRepository();
    }
    public LiveData<TVShowsResponse> searchTVShow(String query , int page){
        return searchTVShowRepository.searchTVShow(query , page);
    }
}
