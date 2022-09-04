package com.rendavis.nycsatscores;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;

import com.jakewharton.rxbinding.view.RxView;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.subjects.PublishSubject;

public abstract class BaseRecyclerViewAdapter<T, B extends ViewBinding>
        extends RecyclerView.Adapter<BaseRecyclerViewAdapter.ViewHolder<B>> {

    public class ClickedView {
        public final View view;
        public final T item;

        public ClickedView(final View view, final T item) {
            this.view = view;
            this.item = item;
        }
    }

    protected List<T> mItems = Collections.emptyList();

    private final PublishSubject<ClickedView> mViewClickSubject = PublishSubject.create();
    public Observable<ClickedView> onViewClicked() {
        return mViewClickSubject;
    }

    public BaseRecyclerViewAdapter() {}
    public BaseRecyclerViewAdapter(final List<T> items) {
        mItems = items;
    }

    protected abstract B createViewBinding(@NonNull ViewGroup parent);

    @NonNull
    @Override
    public ViewHolder<B> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder<>(createViewBinding(parent));
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onDetachedFromRecyclerView(recyclerView);
        mViewClickSubject.onCompleted();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder<B> holder, int position) {
        RxView.clicks(holder.itemView)
                .map(__ -> new ClickedView(holder.itemView, mItems.get(position)))
                .subscribe(mViewClickSubject);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setItems(final List<T> items) {
        mItems = items;
        notifyDataSetChanged();
    }

    static class ViewHolder<B extends ViewBinding> extends RecyclerView.ViewHolder {
        public final B binding;

        ViewHolder(final B binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
