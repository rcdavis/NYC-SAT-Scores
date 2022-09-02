package com.rendavis.nycsatscores.school;

import java.util.List;

import io.reactivex.Observable;

public interface SchoolApi {
    Observable<List<School>> getAllSchools();
}
