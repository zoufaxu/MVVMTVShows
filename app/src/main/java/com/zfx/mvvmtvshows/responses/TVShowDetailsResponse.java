package com.zfx.mvvmtvshows.responses;

import com.google.gson.annotations.SerializedName;
import com.zfx.mvvmtvshows.models.TVShowDetails;

public class TVShowDetailsResponse {
    @SerializedName("tvShow")
    private TVShowDetails tvShowDetails;

    public TVShowDetails getTvShowDetails() {
        return tvShowDetails;
    }
}
