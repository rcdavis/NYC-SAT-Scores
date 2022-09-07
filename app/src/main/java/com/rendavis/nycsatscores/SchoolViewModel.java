package com.rendavis.nycsatscores;

import androidx.lifecycle.ViewModel;

import com.rendavis.nycsatscores.school.School;
import com.rendavis.nycsatscores.school.SchoolRepository;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class SchoolViewModel extends ViewModel {
    private final SchoolRepository schoolRepository = new SchoolRepository();

    private School selectedSchool;

    public void updateSelectedSchool(final School school) {
        selectedSchool = school;
    }

    public Observable<School> getSelectedSchool() {
        if (selectedSchool == null)
            return Observable.empty();

        return Observable.just(selectedSchool);
    }

    public Observable<List<School>> getAllSchools() {
        return schoolRepository.getAllSchools();
    }

    public Observable<School> getSchool(final String id) {
        return schoolRepository.getSchool(id);
    }
}
