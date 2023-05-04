package com.zfx.mvvmtvshows.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zfx.mvvmtvshows.responses.TVShowsResponse;
import com.zfx.mvvmtvshows.network.ApiClient;
import com.zfx.mvvmtvshows.network.ApiService;

import io.reactivex.annotations.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MostPopularTVShowsRepository {
    private ApiService apiService;

    public MostPopularTVShowsRepository() {
        apiService = ApiClient.getClient().create(ApiService.class);

    }

    public LiveData<TVShowsResponse> getMostPopularTVShows(int page){
        MutableLiveData<TVShowsResponse> data = new MutableLiveData<>();
        apiService.getMostPopularTVShows(page).enqueue(new Callback<TVShowsResponse>() {
            @Override
            public void onResponse(@NonNull Call<TVShowsResponse> call, @NonNull Response<TVShowsResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<TVShowsResponse> call, @NonNull Throwable t) {
                Log.d("getMessage",t.getMessage());
                Log.d("toString",t.toString());
                data.setValue(null);
            }
        });
        return data;
    }








}
