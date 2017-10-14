package com.ai.moviedbapp.entities.responses;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public final class ConfigurationResponse {

    @SerializedName("images")
    private Images mImages;

    public Images getImages() {
        return mImages;
    }

    public class Images {

        @SerializedName("secure_base_url")
        private String mSecurebaseUrl;

        @SerializedName("poster_sizes")
        private List<String> mPosterSizes;

        public String getSecureBaseUrl() {
            return mSecurebaseUrl;
        }

        public List<String> getPosterSizes() {
            return mPosterSizes;
        }
    }
}
