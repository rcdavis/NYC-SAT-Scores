package com.rendavis.nycsatscores;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.commons.lang3.function.FailableBiConsumer;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {

    private final List<T> mItems;
    private final FailableBiConsumer<View, T, Throwable> onClickView;

    public BaseRecyclerViewAdapter(
        final List<T> items,
        final FailableBiConsumer<View, T, Throwable> onClickView
    ) {
        mItems = items;
        this.onClickView = onClickView;
    }

    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
