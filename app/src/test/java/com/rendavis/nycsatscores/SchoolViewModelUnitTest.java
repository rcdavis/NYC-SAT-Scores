package com.rendavis.nycsatscores;

import com.rendavis.nycsatscores.school.School;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SchoolViewModelUnitTest {
    @Mock
    private SchoolViewModel viewModel;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(SchoolViewModelUnitTest.class);
    }

    @Test
    public void getSelectedSchool_ReturnsEmpty() {
        //TestSubscriber<School> testSubscriber = new TestSubscriber<>();
        //viewModel.getSelectedSchool().subscribe(testSubscriber);
    }
}
