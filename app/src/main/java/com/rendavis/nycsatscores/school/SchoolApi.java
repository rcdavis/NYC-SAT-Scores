package com.rendavis.nycsatscores.school;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface SchoolApi {
    @GET("s3k6-pzi2.json")
    Observable<List<School>> getAllSchools();

    @GET("f9bf-2cp4.json")
    Observable<List<SchoolSATDTO>> getAllSATScores();
}
