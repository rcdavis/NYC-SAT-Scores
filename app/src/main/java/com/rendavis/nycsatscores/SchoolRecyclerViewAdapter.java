package com.rendavis.nycsatscores;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rendavis.nycsatscores.databinding.SchoolListContentBinding;
import com.rendavis.nycsatscores.school.School;

import org.apache.commons.lang3.function.FailableBiConsumer;

import java.util.List;

public class SchoolRecyclerViewAdapter
        extends BaseRecyclerViewAdapter<School, SchoolRecyclerViewAdapter.ViewHolder> {

    SchoolRecyclerViewAdapter(
            List<School> items,
            final FailableBiConsumer<View, School, Throwable> onClickView
    ) {
        super(items, onClickView);
    }

    @NonNull
    @Override
    public SchoolRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final SchoolListContentBinding binding = SchoolListContentBinding
                .inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new SchoolRecyclerViewAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final SchoolRecyclerViewAdapter.ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        holder.schoolNameText.setText(mItems.get(position).getName());
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView schoolNameText;

        ViewHolder(SchoolListContentBinding binding) {
            super(binding.getRoot());
            schoolNameText = binding.schoolName;
        }
    }
}
