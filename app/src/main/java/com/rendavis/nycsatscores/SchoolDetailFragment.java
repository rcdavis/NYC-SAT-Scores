package com.rendavis.nycsatscores;

import android.content.ClipData;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.rendavis.nycsatscores.databinding.FragmentSchoolDetailBinding;
import com.rendavis.nycsatscores.placeholder.PlaceholderContent;
import com.rendavis.nycsatscores.school.School;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link SchoolListFragment}
 * in two-pane mode (on larger screen devices) or self-contained
 * on handsets.
 */
public class SchoolDetailFragment extends Fragment {

    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The placeholder content this fragment is presenting.
     */
    private School mItem;

    private final View.OnDragListener dragListener = (v, event) -> {
        if (event.getAction() == DragEvent.ACTION_DROP) {
            final ClipData.Item clipDataItem = event.getClipData().getItemAt(0);
            mItem = PlaceholderContent.SCHOOL_MAP.get(clipDataItem.getText().toString());
            updateContent();
        }
        return true;
    };

    private FragmentSchoolDetailBinding binding;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public SchoolDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final Bundle args = getArguments();
        if (args != null && args.containsKey(ARG_ITEM_ID)) {
            // Load the placeholder content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = PlaceholderContent.SCHOOL_MAP.get(args.getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {
        binding = FragmentSchoolDetailBinding.inflate(inflater, container, false);
        View rootView = binding.getRoot();

        // Show the placeholder content as text in a TextView & in the toolbar if available.
        updateContent();
        rootView.setOnDragListener(dragListener);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void updateContent() {
        if (mItem != null) {
            binding.itemDetail.setText(mItem.getOverview());
            if (binding.toolbarLayout != null) {
                binding.toolbarLayout.setTitle(mItem.getName());
            }
        }
    }
}