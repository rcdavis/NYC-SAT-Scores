package com.rendavis.nycsatscores;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
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
    /**
     * Method to intercept global key events in the
     * item list fragment to trigger keyboard shortcuts
     * Currently provides a toast when Ctrl + Z and Ctrl + F
     * are triggered
     */
    ViewCompat.OnUnhandledKeyEventListenerCompat unhandledKeyEventListenerCompat = (v, event) -> {
        if (event.getKeyCode() == KeyEvent.KEYCODE_Z && event.isCtrlPressed()) {
            Toast.makeText(
                v.getContext(),
                "Undo (Ctrl + Z) shortcut triggered",
                Toast.LENGTH_LONG
            ).show();
            return true;
        } else if (event.getKeyCode() == KeyEvent.KEYCODE_F && event.isCtrlPressed()) {
            Toast.makeText(
                v.getContext(),
                "Find (Ctrl + F) shortcut triggered",
                Toast.LENGTH_LONG
            ).show();
            return true;
        }
        return false;
    };

    @Override
    Class<SchoolViewModel> getViewModelClass() {
        return SchoolViewModel.class;
    }

    @Override
    FragmentSchoolListBinding getBinding(@NonNull LayoutInflater inflater, ViewGroup container) {
        return FragmentSchoolListBinding.inflate(inflater, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewCompat.addOnUnhandledKeyEventListener(view, unhandledKeyEventListenerCompat);

        // Leaving this not using view binding as it relies on if the view is visible the current
        // layout configuration (layout, layout-sw600dp)
        setupRecyclerView(binding.itemList, view.findViewById(R.id.item_detail_nav_container));
    }

    private void setupRecyclerView(
        final RecyclerView recyclerView,
        final View itemDetailFragmentContainer
    ) {
        addDisposable(viewModel.getAllSchools()
                .subscribe(schools -> recyclerView.setAdapter(new SchoolRecyclerViewAdapter(
                    schools,
                    itemDetailFragmentContainer,
                    this::onClickView
                ))));
    }

    private void onClickView(final View view, final School school) {
        viewModel.updateSelectedSchool(school);
    }
}