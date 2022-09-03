package com.rendavis.nycsatscores;

import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.rendavis.nycsatscores.databinding.FragmentSchoolDetailBinding;
import com.rendavis.nycsatscores.placeholder.PlaceholderContent;
import com.rendavis.nycsatscores.school.School;

import io.reactivex.disposables.CompositeDisposable;

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

    private School mItem;

    private final View.OnDragListener dragListener = (v, event) -> {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            final ClipData.Item clipDataItem = event.getClipData().getItemAt(0);

            addDisposable(viewModel.getSchool(clipDataItem.getText().toString())
                    .subscribe(school -> {
                        mItem = school;
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

        getSelectedSchool();

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
        if (mItem != null) {
            binding.itemDetail.setText(mItem.getOverview());
            if (binding.phoneNumber != null)
                binding.phoneNumber.setText(mItem.getPhoneNumberString());
            if (binding.toolbarLayout != null)
                binding.toolbarLayout.setTitle(mItem.getName());
        }
    }

    private void getSelectedSchool() {
        final Bundle args = getArguments();
        if (args != null && args.containsKey(ARG_ITEM_ID)) {
            addDisposable(viewModel.getSchool(args.getString(ARG_ITEM_ID))
                    .subscribe(school -> {
                        mItem = school;
                        updateContent();
                    }));
        }
    }
}