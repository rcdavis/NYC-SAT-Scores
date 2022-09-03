package com.rendavis.nycsatscores.school;

import android.util.Log;

import com.rendavis.nycsatscores.BuildConfig;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SchoolRepository {
    private final SchoolLocalDataSource mLocalDataSource = new SchoolLocalDataSource();
    private final SchoolApi schoolApi = createSchoolApi();
    private School mSelectedSchool;

    public Observable<School> getSchool(final String id) {
        if (mSelectedSchool != null && mSelectedSchool.getId().equals(id))
            return Observable.just(mSelectedSchool);

        return mLocalDataSource.getSchool(id);
    }

    public Observable<List<School>> getAllSchools() {
        return Observable.fromCallable(() -> {
            final List<School> cachedSchools = mLocalDataSource.getAllSchools().blockingFirst();
            if (!cachedSchools.isEmpty()) {
                Log.d("Schools", "Returning cached schools...");
                return cachedSchools;
            }

            final List<School> schools = schoolApi.getAllSchools().blockingFirst();
            mLocalDataSource.setSchools(schools);
            Log.d("Schools", "Returning web service schools...");
            return schools;
        })
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());

        /*return schoolApi.getAllSchools()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .onErrorResumeNext(mLocalDataSource.getAllSchools());*/
    }

    public void selectSchool(final School school) {
        mSelectedSchool = school;
    }

    private static SchoolApi createSchoolApi() {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.SCHOOL_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
        return retrofit.create(SchoolApi.class);
    }
}
