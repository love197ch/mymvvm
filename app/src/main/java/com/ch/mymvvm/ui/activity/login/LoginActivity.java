package com.ch.mymvvm.ui.activity.login;

import android.Manifest;

import androidx.annotation.NonNull;

import android.os.Bundle;

import com.ch.mymvvm.BR;
import com.ch.mymvvm.R;
import com.ch.mymvvm.app.AppViewModelFactory;
import com.ch.mymvvm.base.BaseMvvmActivity;
import com.ch.mymvvm.databinding.ActivityLoginBinding;

import androidx.lifecycle.ViewModelProviders;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;

/**
 * @Author: Administrator
 * @Time: 2019/7/29 15:00
 * @Company：ch
 * @Description: 登录页面
 */

@RuntimePermissions
public class LoginActivity extends BaseMvvmActivity<ActivityLoginBinding, LoginViewModel> {

    @Override
    public int initContentView(Bundle savedInstanceState) {
        return R.layout.activity_login;
    }

    @Override
    public int initVariableId() {
        return BR.viewModel;
    }

    @Override
    public LoginViewModel initViewModel() {
        //使用自定义的ViewModelFactory来创建ViewModel，如果不重写该方法，则默认会调用LoginViewModel(@NonNull Application application)构造方法
        AppViewModelFactory factory = AppViewModelFactory.getInstance(getApplication());
        return ViewModelProviders.of(this, factory).get(LoginViewModel.class);
    }

    @Override
    public void initViewObservable() {
        LoginActivityPermissionsDispatcher.initPermissonWithPermissionCheck(this);
    }


    //有权限时会直接调用改方法，没权限时，会在申请通过后调用
    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void initPermisson() {

    }

    //重写该方法之后，当弹出授权对话框时，我们点击允许授权成功时，会自动执行注解@NeedsPermission所标注的方法里面的逻辑
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        LoginActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    /**
     * 被用户拒绝
     */
    @NeedsPermission({Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onPermissionDenied() {
        show_Toast("权限未授予，部分功能无法使用");
    }
}
