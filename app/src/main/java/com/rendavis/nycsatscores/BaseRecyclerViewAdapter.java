package com.rendavis.nycsatscores;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.apache.commons.lang3.function.FailableBiConsumer;

import java.util.List;

public abstract class BaseRecyclerViewAdapter<T, VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {

    protected final List<T> mItems;
    private final FailableBiConsumer<View, T, Throwable> onClickView;

    public BaseRecyclerViewAdapter(
        final List<T> items,
        final FailableBiConsumer<View, T, Throwable> onClickView
    ) {
        mItems = items;
        this.onClickView = onClickView;
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        if (onClickView != null) {
            holder.itemView.setOnClickListener(view -> {
                try {
                    onClickView.accept(view, mItems.get(position));
                } catch (final Throwable e) {
                    e.printStackTrace();
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}
