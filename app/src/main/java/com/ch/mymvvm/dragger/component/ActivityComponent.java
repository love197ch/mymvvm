package com.ch.mymvvm.dragger.component;

import android.app.Activity;

import com.ch.mymvvm.dragger.ActivityScope;
import com.ch.mymvvm.dragger.module.ActivityModule;
import com.ch.mymvvm.ui.activity.login.LoginActivity;

import dagger.Component;

/**
 * @author Administrator
 */
@ActivityScope
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    Activity getActivity();

    void inject(LoginActivity loginActivity);


}
