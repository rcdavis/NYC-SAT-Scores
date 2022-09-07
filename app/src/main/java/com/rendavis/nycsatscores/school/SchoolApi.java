package com.rendavis.nycsatscores.school;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SchoolApi {
    @GET("s3k6-pzi2.json?$select=dbn, school_name, overview_paragraph, phone_number, website, primary_address_line_1, city, zip, state_code, latitude, longitude")
    Observable<List<SchoolDTO>> getAllSchools();

    @GET("s3k6-pzi2.json?$select=dbn, school_name, overview_paragraph, phone_number, website, primary_address_line_1, city, zip, state_code, latitude, longitude&$where=dbn = '{id}'")
    Observable<SchoolDTO> getSchool(@Path("id") final String id);

    @GET("f9bf-2cp4.json")
    Observable<List<SchoolSATDTO>> getAllSATScores();
}
