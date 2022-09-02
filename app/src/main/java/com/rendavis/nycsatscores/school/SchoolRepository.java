package com.rendavis.nycsatscores.school;

import java.util.List;

import io.reactivex.Observable;

public class SchoolRepository {
    private final SchoolLocalDataSource mLocalDataSource = new SchoolLocalDataSource();

    public Observable<School> getSchool(final String id) {
        return mLocalDataSource.getSchool(id);
    }

    public Observable<List<School>> getAllSchools() {
        return mLocalDataSource.getAllSchools();
    }
}
