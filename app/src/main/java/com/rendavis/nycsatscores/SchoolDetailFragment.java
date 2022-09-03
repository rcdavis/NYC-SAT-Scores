package com.rendavis.nycsatscores;

import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
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
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    private final View.OnDragListener dragListener = (v, event) -> {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            final ClipData.Item clipDataItem = event.getClipData().getItemAt(0);

            addDisposable(viewModel.getSchool(clipDataItem.getText().toString())
                    .subscribe(school -> {
                        viewModel.updateSelectedSchool(school);
                        updateContent();
                    }));
        }
        return true;
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SchoolDetailFragment() {
    }

    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {
        final View rootView = super.onCreateView(inflater, container, savedInstanceState);
        if (rootView != null)
            rootView.setOnDragListener(dragListener);

        updateContent();

        return rootView;
    }

    @Override
    Class<SchoolViewModel> getViewModelClass() {
        return SchoolViewModel.class;
    }

    @Override
    FragmentSchoolDetailBinding getBinding(@NonNull LayoutInflater inflater, ViewGroup container) {
        return FragmentSchoolDetailBinding.inflate(inflater, container, false);
    }

    private void updateContent() {
        addDisposable(viewModel.getSelectedSchool()
                .subscribe(school -> {
                    if (school != null) {
                        binding.itemDetail.setText(school.getOverview());
                        if (binding.phoneNumber != null)
                            binding.phoneNumber.setText(school.getPhoneNumberString());
                        if (binding.toolbarLayout != null)
                            binding.toolbarLayout.setTitle(school.getName());
                    }
                }));
    }
}