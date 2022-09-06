package com.rendavis.nycsatscores.school;

import com.rendavis.nycsatscores.util.CollectionUtils;
import com.rendavis.nycsatscores.util.RetrofitUtils;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SchoolRepository {
    private final SchoolLocalDataSource mLocalDataSource = new SchoolLocalDataSource();
    private final SchoolApi schoolApi = RetrofitUtils.createSchoolApi();

    public SchoolRepository() {}

    public Observable<School> getSchool(final String id) {
        return mLocalDataSource.getSchool(id)
                .onErrorResumeNext(schoolApi.getSchool(id).map(School::from));
    }

    public Observable<List<School>> getAllSchools() {
        return mLocalDataSource.getAllSchools()
                .onErrorResumeNext(
                    Observable.zip(
                        schoolApi.getAllSchools(),
                        schoolApi.getAllSATScores(),
                        this::zipDTOLists
                    )
                    .doOnNext(mLocalDataSource::setSchools)
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<SATScores>> getSATScores() {
        return schoolApi.getAllSATScores()
                .map(dtos -> CollectionUtils.mapList(dtos, SATScores::from));
    }

    private List<School> zipDTOLists(
        final List<SchoolDTO> schoolDTOs, final List<SchoolSATDTO> satDTOs
    ) {
        return CollectionUtils.zipLists(schoolDTOs, satDTOs,
                (schooldto, satdto) -> StringUtils.equalsIgnoreCase(schooldto.id, satdto.id),
                School::from);
    }
}
