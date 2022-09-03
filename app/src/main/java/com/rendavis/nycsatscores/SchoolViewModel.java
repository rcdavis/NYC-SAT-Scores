package com.rendavis.nycsatscores;

import androidx.lifecycle.ViewModel;

import com.rendavis.nycsatscores.placeholder.PlaceholderContent;
import com.rendavis.nycsatscores.school.School;

import java.util.List;

import io.reactivex.Observable;

public class SchoolViewModel extends ViewModel {
    private School mSelectedSchool;

    public void selectSchool(final School school) {
        mSelectedSchool = school;
    }

    public Observable<List<School>> getAllSchools() {
        return PlaceholderContent.SCHOOL_REPO.getAllSchools();
    }

    public Observable<School> getSchool(final String id) {
        return PlaceholderContent.SCHOOL_REPO.getSchool(id);
    }
}
