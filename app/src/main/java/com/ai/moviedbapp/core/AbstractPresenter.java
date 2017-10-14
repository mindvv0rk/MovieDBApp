package com.ai.moviedbapp.core;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractPresenter<V extends IView> {
    private V mView;

    public void attachView(@NonNull V view) {
        mView = view;
        onViewAttached(view);
    }

    public void detachView() {
        onViewDetached();
        mView = null;
    }

    public boolean isAttached() {
        return mView != null;
    }

    public V getViews() {
        return mView;
    }

    protected void onCreate() {
    }

    protected void onViewAttached(@NonNull V view) {
    }

    protected void onViewDetached() {
    }

    protected void onDestroy() {
    }

}
