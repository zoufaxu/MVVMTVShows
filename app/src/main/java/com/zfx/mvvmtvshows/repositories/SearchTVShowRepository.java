package com.zfx.mvvmtvshows.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zfx.mvvmtvshows.network.ApiClient;
import com.zfx.mvvmtvshows.network.ApiService;
import com.zfx.mvvmtvshows.responses.TVShowsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTVShowRepository {
    private ApiService apiService;

    public SearchTVShowRepository() {
        apiService = ApiClient.getClient().create(ApiService.class);
    }
    public LiveData<TVShowsResponse> searchTVShow(String query , int page){
        MutableLiveData<TVShowsResponse> data = new MutableLiveData<>();
        apiService.searchTVShow(query , page).enqueue(new Callback<TVShowsResponse>() {
            @Override
            public void onResponse(Call<TVShowsResponse> call, Response<TVShowsResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TVShowsResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
