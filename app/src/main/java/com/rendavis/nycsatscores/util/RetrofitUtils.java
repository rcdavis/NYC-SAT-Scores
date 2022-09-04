package com.rendavis.nycsatscores.util;

import com.rendavis.nycsatscores.BuildConfig;
import com.rendavis.nycsatscores.school.SchoolApi;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    public static SchoolApi createSchoolApi() {
        return createApi(SchoolApi.class, BuildConfig.SCHOOL_API_URL);
    }

    public static <T> T createApi(final Class<T> tClass, final String baseUrl) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build()
                .create(tClass);
    }
}
