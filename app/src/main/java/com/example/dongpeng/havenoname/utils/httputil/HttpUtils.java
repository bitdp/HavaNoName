package com.example.dongpeng.havenoname.utils.httputil;

import com.example.dongpeng.havenoname.custom.ProgressInterceptor;
import com.example.dongpeng.havenoname.interfac.ProgressListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 日期：16/3/17 20:41
 * <p>
 * 描述：
 * 修复：
 */
public class HttpUtils
{
    public static ApiService createService(ProgressListener progressListener)
    {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder()//
                .readTimeout(5, TimeUnit.SECONDS)//
                .connectTimeout(5, TimeUnit.SECONDS)//
//                .addInterceptor(new HttpLoggingInterceptor()//
//                        .setLevel(HttpLoggingInterceptor.Level.BODY))//
                .addInterceptor(new ProgressInterceptor(progressListener))
                .build();

        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()//
                .baseUrl("http://192.168.12.30:8080/")//
                .client(okHttpClient)//
                .addConverterFactory(GsonConverterFactory.create(gson))//
                .callbackExecutor(executorService)
                .build();

        ApiService apiService = retrofit.create(ApiService.class);

        return apiService;
    }


}
