package com.rizal.perpusonline.utility;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by Rizal Y on 8/1/2016.
 */
public class OkHttpRequest {
    public static Call getDataFromServer(String url) throws IOException {
        Call call = null;

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .build();

        call = client.newCall(request);

        return call;
    }
    public static Call postData(String url,RequestBody params){
        OkHttpClient client = new OkHttpClient.Builder()
                    .connectTimeout(10,TimeUnit.SECONDS)
                    .writeTimeout(10,TimeUnit.SECONDS)
                    .readTimeout(20,TimeUnit.SECONDS)
                    .build();
        Request request = new Request.Builder()
                    .url(url)
                    .post(params)
                    .build();
        return client.newCall(request);
    }
    public static Call putData(String url,RequestBody params){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .put(params)
                .build();
        return client.newCall(request);
    }
    public static Call hapusData(String url){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(20,TimeUnit.SECONDS)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .delete()
                .build();
        return client.newCall(request);
    }
}
