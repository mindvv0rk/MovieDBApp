package com.ai.moviedbapp.interactor;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

public class NetworkState implements INetworkState {

    private ConnectivityManager mConnectivityManager;

    @Inject
    public NetworkState(Context context) {
        mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Override
    public boolean hasNetworkConnection() {
        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }
}
