package com.rendavis.nycsatscores.school;

import com.rendavis.nycsatscores.util.CollectionUtils;
import com.rendavis.nycsatscores.util.RetrofitUtils;

import org.apache.commons.lang3.StringUtils;

import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SchoolRepository {
    private final SchoolLocalDataSource mLocalDataSource = new SchoolLocalDataSource();
    private final SchoolApi schoolApi = RetrofitUtils.createSchoolApi();

    public SchoolRepository() {}

    public Observable<School> getSchool(final String id) {
        return mLocalDataSource.getSchool(id)
                .onErrorResumeWith(schoolApi.getSchool(id).map(School::from))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Observable<List<School>> getAllSchools() {
        return mLocalDataSource.getAllSchools()
                .onErrorResumeWith(
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

    private List<School> zipDTOLists(
        final List<SchoolDTO> schoolDTOs, final List<SchoolSATDTO> satDTOs
    ) {
        try {
            return CollectionUtils.zipLists(schoolDTOs, satDTOs,
                    (schooldto, satdto) -> StringUtils.equalsIgnoreCase(schooldto.id, satdto.id),
                    School::from);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }
}
