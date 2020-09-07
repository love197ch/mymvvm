package com.ch.mymvvm.data.http;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class RxSchedulers {

    final static ObservableTransformer Stf = upstream -> upstream.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread());

    static <T> ObservableTransformer<T, T> applySchedulers() {
        return Stf;
    }

    @SuppressWarnings("unchecked")
    public static <T> ObservableTransformer<T, T> io_main() {
        return (ObservableTransformer<T, T>) applySchedulers();
    }

}
