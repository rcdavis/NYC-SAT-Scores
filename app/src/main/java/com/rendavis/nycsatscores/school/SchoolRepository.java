package com.rendavis.nycsatscores.school;

import android.util.Log;

import com.rendavis.nycsatscores.util.CollectionUtils;
import com.rendavis.nycsatscores.util.RetrofitUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchoolRepository {
    private final SchoolLocalDataSource mLocalDataSource = new SchoolLocalDataSource();
    private final SchoolApi schoolApi = RetrofitUtils.createSchoolApi();
    private School mSelectedSchool;

    public Observable<School> getSchool(final String id) {
        if (mSelectedSchool != null && mSelectedSchool.getId().equals(id))
            return Observable.just(mSelectedSchool);

        return mLocalDataSource.getSchool(id)
                .onErrorResumeNext(schoolApi.getSchool(id).map(School::from));
    }

    public Observable<List<School>> getAllSchools() {
        return Observable.fromCallable(() -> {
            final List<School> cachedSchools = mLocalDataSource.getAllSchools().blockingFirst();
            if (!cachedSchools.isEmpty()) {
                Log.d("Schools", "Returning cached schools...");
                return cachedSchools;
            }

            final List<School> schools = schoolApi.getAllSchools()
                    .map(dtos -> CollectionUtils.mapList(dtos, School::from))
                    .blockingFirst();
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
}
