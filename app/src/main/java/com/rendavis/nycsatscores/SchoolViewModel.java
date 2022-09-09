package com.rendavis.nycsatscores;

import androidx.lifecycle.ViewModel;

import com.rendavis.nycsatscores.school.School;
import com.rendavis.nycsatscores.school.SchoolRepository;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SchoolViewModel extends ViewModel {
    private final SchoolRepository schoolRepository = new SchoolRepository();

    private School selectedSchool;

    public void updateSelectedSchool(final School school) {
        selectedSchool = school;
    }

    public Observable<School> getSelectedSchool() {
        if (selectedSchool == null)
            return Observable.error(new IllegalStateException("No School Selected"));

        return Observable.just(selectedSchool);
    }

    public Observable<List<School>> getAllSchools() {
        return schoolRepository.getAllSchools()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
