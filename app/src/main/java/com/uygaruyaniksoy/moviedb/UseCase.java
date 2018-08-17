package com.uygaruyaniksoy.moviedb;

import android.os.AsyncTask;

import org.json.JSONObject;

public abstract class UseCase<T extends UseCase.Request, R extends UseCase.Response> {
    private T request;
    private Callback<R> callback;

    public abstract void run();

    public T getRequest() {
        return request;
    }

    public void setRequest(T request) {
        this.request = request;
    }

    public Callback<R> getCallback() {
        return callback;
    }

    public void setCallback(Callback<R> callback) {
        this.callback = callback;
    }

    public interface Request {
    }

    public interface Response {
    }

    public interface Callback<R> {
        void onSuccess(R response);
        void onError();
    }
}
