package com.rendavis.nycsatscores;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.rendavis.nycsatscores.databinding.FragmentSchoolListBinding;
import com.rendavis.nycsatscores.databinding.SchoolListContentBinding;
import com.rendavis.nycsatscores.placeholder.PlaceholderContent;
import com.rendavis.nycsatscores.school.School;

import java.util.List;

import io.reactivex.disposables.CompositeDisposable;

/**
 * A fragment representing a list of Items. This fragment
 * has different presentations for handset and larger screen devices. On
 * handsets, the fragment presents a list of items, which when touched,
 * lead to a {@link SchoolDetailFragment} representing
 * item details. On larger screens, the Navigation controller presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class SchoolListFragment extends Fragment {

    private FragmentSchoolListBinding binding;

    private final CompositeDisposable mDisposables = new CompositeDisposable();

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
    public View onCreateView(
        @NonNull LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {
        binding = FragmentSchoolListBinding.inflate(inflater, container, false);
        return binding.getRoot();
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
        mDisposables.add(PlaceholderContent.SCHOOL_REPO.getAllSchools()
                .subscribe(schools -> {
                    recyclerView.setAdapter(new SchoolRecyclerViewAdapter(
                        schools,
                        itemDetailFragmentContainer
                    ));
                }));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public static class SchoolRecyclerViewAdapter
            extends RecyclerView.Adapter<SchoolRecyclerViewAdapter.ViewHolder> {

        private final List<School> mValues;
        private final View mItemDetailFragmentContainer;

        SchoolRecyclerViewAdapter(
            List<School> items,
            View itemDetailFragmentContainer
        ) {
            mValues = items;
            mItemDetailFragmentContainer = itemDetailFragmentContainer;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            final SchoolListContentBinding binding = SchoolListContentBinding
                    .inflate(LayoutInflater.from(parent.getContext()), parent, false);
            return new ViewHolder(binding);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.schoolNameText.setText(mValues.get(position).getName());

            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(itemView -> {
                final School item = (School) itemView.getTag();
                PlaceholderContent.SCHOOL_REPO.selectSchool(item);
                final Bundle arguments = new Bundle();
                arguments.putString(SchoolDetailFragment.ARG_ITEM_ID, item.getId());
                if (mItemDetailFragmentContainer != null) {
                    Navigation.findNavController(mItemDetailFragmentContainer)
                            .navigate(R.id.fragment_item_detail, arguments);
                } else {
                    Navigation.findNavController(itemView)
                            .navigate(R.id.show_item_detail, arguments);
                }
            });
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                /*
                 * Context click listener to handle Right click events
                 * from mice and trackpad input to provide a more native
                 * experience on larger screen devices
                 */
                holder.itemView.setOnContextClickListener(v -> {
                    final School item = (School) holder.itemView.getTag();
                    Toast.makeText(
                            holder.itemView.getContext(),
                            "Context click of item " + item.getId(),
                            Toast.LENGTH_LONG
                    ).show();
                    return true;
                });
            }
            holder.itemView.setOnLongClickListener(v -> {
                // Setting the item id as the clip data so that the drop target is able to
                // identify the id of the content
                final ClipData.Item clipItem = new ClipData.Item(mValues.get(position).getId());
                final ClipData dragData = new ClipData(
                        ((School) v.getTag()).getOverview(),
                        new String[]{ClipDescription.MIMETYPE_TEXT_PLAIN},
                        clipItem
                );

                if (Build.VERSION.SDK_INT >= 24) {
                    v.startDragAndDrop(
                            dragData,
                            new View.DragShadowBuilder(v),
                            null,
                            0
                    );
                } else {
                    v.startDrag(
                            dragData,
                            new View.DragShadowBuilder(v),
                            null,
                            0
                    );
                }
                return true;
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        static class ViewHolder extends RecyclerView.ViewHolder {
            final TextView schoolNameText;

            ViewHolder(SchoolListContentBinding binding) {
                super(binding.getRoot());
                schoolNameText = binding.schoolName;
            }
        }
    }
}