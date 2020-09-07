package com.ch.mymvvm.dragger.module;

import com.ch.mymvvm.app.MyApplication;
import com.ch.mymvvm.dragger.ContextLife;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Administrator
 */
@Module
public class AppModule {
    private final MyApplication application;

    public AppModule(MyApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    @ContextLife("Application")
    MyApplication provideApplicationContext() {
        return application;
    }

}
