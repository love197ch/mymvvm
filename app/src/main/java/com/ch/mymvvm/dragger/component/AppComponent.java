package com.ch.mymvvm.dragger.component;


import com.ch.mymvvm.app.MyApplication;
import com.ch.mymvvm.dragger.ContextLife;
import com.ch.mymvvm.dragger.module.AppModule;


import javax.inject.Singleton;

import dagger.Component;

/**
 * @author Administrator
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    /**
     * 提供App的Context
     * @return
     */
    @ContextLife("Application")
    MyApplication getContext();

}
