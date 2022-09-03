package com.rendavis.nycsatscores.school;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

public class SchoolLocalDataSource {
    private final List<School> mSchools = new ArrayList<>();
    private final Map<String, School> mSchoolMap = new HashMap<>();

    public SchoolLocalDataSource() {
    }

    public Observable<School> getSchool(final String id) {
        final School school = mSchoolMap.get(id);
        if (school == null)
            return Observable.empty();

        return Observable.just(school);
    }

    public Observable<List<School>> getAllSchools() {
        return Observable.just(mSchools);
    }

    public void setSchools(final List<School> schools) {
        mSchools.clear();
        mSchoolMap.clear();
        for (final School school : schools)
            addSchool(school);
    }

    private void addSchool(final School school) {
        mSchools.add(school);
        mSchoolMap.put(school.getId(), school);
    }
}
