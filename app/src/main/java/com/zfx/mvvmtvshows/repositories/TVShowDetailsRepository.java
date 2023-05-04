package com.zfx.mvvmtvshows.repositories;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.zfx.mvvmtvshows.network.ApiClient;
import com.zfx.mvvmtvshows.network.ApiService;
import com.zfx.mvvmtvshows.responses.TVShowDetailsResponse;
import com.zfx.mvvmtvshows.responses.TVShowsResponse;

import io.reactivex.annotations.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TVShowDetailsRepository {
    private ApiService apiService;

    public TVShowDetailsRepository() {
        apiService = ApiClient.getClient().create(ApiService.class);
    }
    public LiveData<TVShowDetailsResponse> getTVShowDetails(String tvShowId){
        MutableLiveData<TVShowDetailsResponse> data = new MutableLiveData<>();
        apiService.getTVSHowDetails(tvShowId).enqueue(new Callback<TVShowDetailsResponse>() {
            @Override
            public void onResponse(@NonNull Call<TVShowDetailsResponse> call, @NonNull Response<TVShowDetailsResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<TVShowDetailsResponse> call, @NonNull Throwable t) {
                Log.d("getMessage",t.getMessage());
                Log.d("toString",t.toString());
                data.setValue(null);
            }
        });
        return data;
    }
}
