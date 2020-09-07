package com.ch.mymvvm.dragger.component;

import android.app.Activity;

import com.ch.mymvvm.dragger.FragmentScope;
import com.ch.mymvvm.dragger.module.FragmentModule;


import dagger.Component;

/**
 * @author Administrator
 */
@FragmentScope
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {

    Activity getActivity();

}
