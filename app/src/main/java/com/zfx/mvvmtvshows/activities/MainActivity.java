package com.zfx.mvvmtvshows.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.zfx.mvvmtvshows.adapters.TVShowsAdapter;
import com.zfx.mvvmtvshows.databinding.ActivityMainBinding;
import com.zfx.mvvmtvshows.listeners.TVShowsListener;
import com.zfx.mvvmtvshows.models.TVShow;
import com.zfx.mvvmtvshows.viewmodels.MostPopularTVShowsViewModel;
import com.zfx.mvvmtvshows.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements TVShowsListener {

    private ActivityMainBinding activityMainBinding;
    private MostPopularTVShowsViewModel viewModel;
    private List<TVShow> tvShows = new ArrayList<>();
    private TVShowsAdapter tvShowsAdapter;
    private int currentPage = 1;
    private int totalAvailablePages = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this , R.layout.activity_main);
        doInitialization();
    }

    private void doInitialization(){
//        setHasFixedSize(true) 告诉 RecyclerView，它所包含的子项的大小是固定的，不会发生变化。
//        这样，RecyclerView 就可以在绘制时使用预先计算的布局大小，而不需要重新计算布局大小，从而提高性能。
//        需要注意的是，只有当 RecyclerView 的布局大小确实是固定的时才应该调用 setHasFixedSize(true)，
//        否则可能导致布局错误。
        activityMainBinding.tvShowRecyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTVShowsViewModel.class);
        tvShowsAdapter = new TVShowsAdapter(tvShows , this);
        activityMainBinding.tvShowRecyclerView.setAdapter(tvShowsAdapter);
        activityMainBinding.tvShowRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!activityMainBinding.tvShowRecyclerView.canScrollVertically(1)){
                    if (currentPage <= totalAvailablePages){
                        currentPage++;
                        getMostPopularTVShows();
                    }
                }
            }
        });
        activityMainBinding.imagewatchlist.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext() , WatchlistActivity.class));
        });
        activityMainBinding.imagesearch.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext() , SearchActivity.class));
        });
        getMostPopularTVShows();
    }
    private void getMostPopularTVShows(){
        toggleLoading();
        viewModel.getMostPopularTVShows(currentPage).observe(this , mostPopularTVShowsResponse -> {
            toggleLoading();
                if (mostPopularTVShowsResponse != null){
                    totalAvailablePages = mostPopularTVShowsResponse.getTotalPages();
                    if (mostPopularTVShowsResponse.getTvShows() != null){
                        int oldCount = tvShows.size();
                        tvShows.addAll(mostPopularTVShowsResponse.getTvShows());
                        tvShowsAdapter.notifyItemRangeInserted(oldCount , tvShows.size());
                    }
                }
            }
        );
    }

    private void toggleLoading(){
        if (currentPage == 1){
            if (activityMainBinding.getIsLoading() != null && activityMainBinding.getIsLoading()){
                activityMainBinding.setIsLoading(false);
            }else {
                activityMainBinding.setIsLoading(true);
            }
        }else {
            if (activityMainBinding.getIsLoadingMore() != null && activityMainBinding.getIsLoadingMore()){
                activityMainBinding.setIsLoadingMore(false);
            }else {
                activityMainBinding.setIsLoadingMore(true);
            }
        }
    }

    @Override
    public void onTVShowClicked(TVShow tvShow) {
        Intent intent = new Intent(getApplicationContext() , TVShowDetailsActivity.class);
        intent.putExtra("tvShow" , tvShow);
        startActivity(intent);
    }











}