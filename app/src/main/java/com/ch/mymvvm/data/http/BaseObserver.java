package com.ch.mymvvm.data.http;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public abstract class BaseObserver<T> implements Observer<T> {
    @Override
    public final void onNext(@NonNull T result) {
        try {
            onSuccess(result);
        }catch (Exception e){
            onFailure(e, RxExceptionUtil.exceptionHandler(e));
        }
    }

    @Override
    public void onError(@NonNull Throwable e) {
        onFailure(e, RxExceptionUtil.exceptionHandler(e));
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
    }

    public abstract void onSuccess(T result);

    public abstract void onFailure(Throwable e, String errorMsg);
}