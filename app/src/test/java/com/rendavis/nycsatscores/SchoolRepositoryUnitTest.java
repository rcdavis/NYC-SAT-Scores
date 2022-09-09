package com.rendavis.nycsatscores;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.rendavis.nycsatscores.school.School;
import com.rendavis.nycsatscores.school.SchoolApi;
import com.rendavis.nycsatscores.school.SchoolDTO;
import com.rendavis.nycsatscores.school.SchoolLocalDataSource;
import com.rendavis.nycsatscores.school.SchoolRepository;
import com.rendavis.nycsatscores.school.SchoolSATDTO;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.observers.TestObserver;

public class SchoolRepositoryUnitTest {
    private SchoolRepository schoolRepository;

    @Mock
    private SchoolLocalDataSource schoolLocalDataSource;

    @Mock
    private SchoolApi schoolApi;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        schoolRepository = new SchoolRepository(schoolLocalDataSource, schoolApi);

        final List<SchoolDTO> schoolDTOS = TestUtils.createMockSchoolDtos();
        final List<SchoolSATDTO> satdtos = TestUtils.createMockSatDtos();
        when(schoolApi.getAllSchools()).thenReturn(Observable.just(schoolDTOS));
        when(schoolApi.getAllSATScores()).thenReturn(Observable.just(satdtos));
    }

    @Test
    public void whenLocalSchoolsEmpty_ThenCallWebApi() {
        final List<School> schools = TestUtils.createMockSchoolsList();
        when(schoolLocalDataSource.getAllSchools())
                .thenReturn(
                    Observable.error(new IllegalStateException("Couldn't get stored schools"))
                );

        final TestObserver<List<School>> testObserver = schoolRepository.getAllSchools().test();
        testObserver.assertResult(schools);

        verify(schoolLocalDataSource, times(1)).getAllSchools();
        verify(schoolApi, times(1)).getAllSchools();
        verify(schoolApi, times(1)).getAllSATScores();
    }

    @Test
    public void whenLocalSchoolsNotEmpty_ThenReturnLocal() {
        final List<School> schools = TestUtils.createMockSchoolsList();
        when(schoolLocalDataSource.getAllSchools()).thenReturn(Observable.just(schools));

        final TestObserver<List<School>> testObserver = schoolRepository.getAllSchools().test();
        testObserver.assertResult(schools);

        verify(schoolLocalDataSource, times(1)).getAllSchools();
    }
}
