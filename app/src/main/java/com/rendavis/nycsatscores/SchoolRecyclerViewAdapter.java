package com.rendavis.nycsatscores;

import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.rendavis.nycsatscores.databinding.SchoolListContentBinding;
import com.rendavis.nycsatscores.school.School;

import org.apache.commons.lang3.function.FailableBiConsumer;

import java.util.List;

public class SchoolRecyclerViewAdapter
        extends RecyclerView.Adapter<SchoolRecyclerViewAdapter.ViewHolder> {

    private final List<School> mValues;
    private final View mItemDetailFragmentContainer;
    private final FailableBiConsumer<View, School, Throwable> onClickView;

    SchoolRecyclerViewAdapter(
            List<School> items,
            View itemDetailFragmentContainer,
            final FailableBiConsumer<View, School, Throwable> onClickView
    ) {
        mValues = items;
        mItemDetailFragmentContainer = itemDetailFragmentContainer;
        this.onClickView = onClickView;
    }

    @NonNull
    @Override
    public SchoolRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final SchoolListContentBinding binding = SchoolListContentBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SchoolRecyclerViewAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final SchoolRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.schoolNameText.setText(mValues.get(position).getName());

        holder.itemView.setOnClickListener(itemView -> {
            final School school = mValues.get(position);
            if (onClickView != null) {
                try {
                    onClickView.accept(itemView, school);
                } catch (final Throwable e) {
                    e.printStackTrace();
                }
            }
            final Bundle arguments = new Bundle();
            arguments.putString(SchoolDetailFragment.ARG_ITEM_ID, school.getId());
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
                final School school = mValues.get(position);
                Toast.makeText(
                        holder.itemView.getContext(),
                        "Context click of item " + school.getId(),
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
