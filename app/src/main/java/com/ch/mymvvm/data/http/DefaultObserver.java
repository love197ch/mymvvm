package com.ch.mymvvm.data.http;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import me.goldze.mvvmhabit.base.BaseViewModel;

public abstract class DefaultObserver<T> extends BaseObserver<T> {
    private BaseViewModel viewModel;

    public DefaultObserver(BaseViewModel viewModel) {
        this.viewModel = viewModel;
    }

    //等待上传的加载框
    public void dialogShow() {
        viewModel.showDialog();
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        if (!d.isDisposed()) {
            dialogShow();
        }
    }


    @Override
    public void onComplete() {
        viewModel.dismissDialog();
    }

    @Override
    public void onError(@NonNull Throwable e) {
        super.onError(e);
        viewModel.dismissDialog();
    }

}
