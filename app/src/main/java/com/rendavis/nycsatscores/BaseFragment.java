package com.rendavis.nycsatscores;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

public abstract class BaseFragment<V extends ViewModel, B extends ViewBinding> extends Fragment {
    protected V viewModel;
    protected B binding;

    private final CompositeDisposable mDisposables = new CompositeDisposable();

    public void addDisposable(final Disposable disposable) {
        mDisposables.add(disposable);
    }

    @Override
    public View onCreateView(
        @NonNull LayoutInflater inflater,
        ViewGroup container,
        Bundle savedInstanceState
    ) {
        binding = createViewBinding(inflater, container);
        viewModel = new ViewModelProvider(requireActivity()).get(getViewModelClass());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        onInit(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mDisposables.clear();
        binding = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mDisposables.dispose();
    }

    abstract Class<V> getViewModelClass();

    abstract B createViewBinding(@NonNull LayoutInflater inflater, ViewGroup container);

    abstract void onInit(Bundle savedInstanceState);
}
