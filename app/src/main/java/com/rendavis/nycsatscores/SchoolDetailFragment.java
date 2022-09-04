package com.rendavis.nycsatscores;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.rendavis.nycsatscores.databinding.FragmentSchoolDetailBinding;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link SchoolListFragment}
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */
public class SchoolDetailFragment
        extends BaseFragment<SchoolViewModel, FragmentSchoolDetailBinding> {

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SchoolDetailFragment() {}

    @Override
    void onInit(Bundle savedInstanceState) {
        addDisposable(viewModel.getSelectedSchool()
                .subscribe(school -> binding.setSchool(school)));
    }

    @Override
    Class<SchoolViewModel> getViewModelClass() {
        return SchoolViewModel.class;
    }

    @Override
    FragmentSchoolDetailBinding createViewBinding(
        @NonNull LayoutInflater inflater, ViewGroup container
    ) {
        return FragmentSchoolDetailBinding.inflate(inflater, container, false);
    }
}