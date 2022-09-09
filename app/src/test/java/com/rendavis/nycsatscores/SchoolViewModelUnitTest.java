package com.rendavis.nycsatscores;

import com.rendavis.nycsatscores.school.School;

import org.junit.Before;
import org.junit.Test;

import io.reactivex.rxjava3.observers.TestObserver;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class SchoolViewModelUnitTest {
    private SchoolViewModel viewModel;

    @Before
    public void setup() {
        viewModel = new SchoolViewModel();
    }

    @Test
    public void getSelectedSchool_WhenDefault_ReturnsError() {
        final TestObserver<School> testObserver = viewModel.getSelectedSchool().test();

        testObserver.assertError(IllegalStateException.class);
    }

    @Test
    public void getSelectedSchool_WhenUpdateNullSelectedSchool_ReturnsError() {
        viewModel.updateSelectedSchool(null);

        final TestObserver<School> testObserver = viewModel.getSelectedSchool().test();

        testObserver.assertError(IllegalStateException.class);
    }

    @Test
    public void getSelectedSchool_ReturnsSelectedSchool() {
        final School school = TestUtils.createMockSchool();

        viewModel.updateSelectedSchool(school);
        final TestObserver<School> testObserver = viewModel.getSelectedSchool().test();

        testObserver.assertResult(school);
    }
}
