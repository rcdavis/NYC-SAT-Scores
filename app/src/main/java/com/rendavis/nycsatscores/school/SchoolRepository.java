package com.rendavis.nycsatscores.school;

import java.util.List;

public class SchoolRepository {
    private final SchoolLocalDataSource mLocalDataSource = new SchoolLocalDataSource();

    public School getSchool(final String id) {
        return mLocalDataSource.getSchool(id);
    }

    public List<School> getAllSchools() {
        return mLocalDataSource.getAllSchools();
    }
}
