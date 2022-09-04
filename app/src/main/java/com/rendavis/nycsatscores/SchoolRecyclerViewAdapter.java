package com.rendavis.nycsatscores;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.rendavis.nycsatscores.databinding.SchoolListContentBinding;
import com.rendavis.nycsatscores.school.School;

import java.util.List;

public class SchoolRecyclerViewAdapter
        extends BaseRecyclerViewAdapter<School, SchoolListContentBinding> {

    public SchoolRecyclerViewAdapter() {
        super();
    }

    public SchoolRecyclerViewAdapter(final List<School> items) {
        super(items);
    }

    @Override
    protected SchoolListContentBinding createViewBinding(@NonNull ViewGroup parent) {
        return SchoolListContentBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
    }

    @Override
    public void onBindViewHolder(
        @NonNull final BaseRecyclerViewAdapter.ViewHolder<SchoolListContentBinding> holder,
        int position
    ) {
        super.onBindViewHolder(holder, position);
        holder.binding.schoolName.setText(mItems.get(position).getName());
    }
}
