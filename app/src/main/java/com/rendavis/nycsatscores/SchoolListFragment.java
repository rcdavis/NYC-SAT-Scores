package com.rendavis.nycsatscores;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.rendavis.nycsatscores.databinding.FragmentSchoolListBinding;
import com.rendavis.nycsatscores.school.School;

/**
 * A fragment representing a list of Items. This fragment
 * has different presentations for handset and larger screen devices. On
 * handsets, the fragment presents a list of items, which when touched,
 * lead to a {@link SchoolDetailFragment} representing
 * item details. On larger screens, the Navigation controller presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class SchoolListFragment extends BaseFragment<SchoolViewModel, FragmentSchoolListBinding> {

    @Override
    Class<SchoolViewModel> getViewModelClass() {
        return SchoolViewModel.class;
    }

    @Override
    FragmentSchoolListBinding createViewBinding(
        @NonNull LayoutInflater inflater, ViewGroup container
    ) {
        return FragmentSchoolListBinding.inflate(inflater, container, false);
    }

    @Override
    void onInit(Bundle savedInstanceState) {
        setupRecyclerView(binding.itemList);
    }

    private void setupRecyclerView(final RecyclerView recyclerView) {
        final SchoolRecyclerViewAdapter adapter = new SchoolRecyclerViewAdapter();
        recyclerView.setAdapter(adapter);

        addDisposable(viewModel.getAllSchools()
                .subscribe(adapter::setItems));

        adapter.onViewClicked()
                .subscribe(clickedView -> onClickView(clickedView.view, clickedView.item));
    }

    private void onClickView(final View view, final School school) {
        viewModel.updateSelectedSchool(school);
        if (binding.itemDetailNavContainer != null) {
            Navigation.findNavController(binding.itemDetailNavContainer)
                    .navigate(R.id.fragment_item_detail);
        } else {
            Navigation.findNavController(view).navigate(R.id.show_item_detail);
        }
    }
}