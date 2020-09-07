package com.ch.mymvvm.ui.activity.login;

import android.app.Application;
import android.text.TextUtils;

import com.ch.mymvvm.bean.Item;
import com.ch.mymvvm.data.DataRepository;
import com.ch.mymvvm.data.http.BaseResponse;
import com.ch.mymvvm.data.http.DefaultObserver;
import com.ch.mymvvm.data.http.RxSchedulers;
import com.ch.mymvvm.response.LoginResponse;
import com.ch.mymvvm.ui.activity.main.MainActivity;
import com.ch.mymvvm.utils.ToastUtils;
import com.uber.autodispose.AutoDispose;
import com.uber.autodispose.android.lifecycle.AndroidLifecycleScopeProvider;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.LifecycleOwner;
import me.goldze.mvvmhabit.base.BaseViewModel;
import me.goldze.mvvmhabit.binding.command.BindingAction;
import me.goldze.mvvmhabit.binding.command.BindingCommand;
import me.goldze.mvvmhabit.utils.SPUtils;

/**
 * @Author: Administrator
 * @Time: 2020/9/1 16:36
 * @Company：ch
 * @Description: 功能描述
 */
public class LoginViewModel extends BaseViewModel<DataRepository> {
    //用户名的绑定
    public ObservableField<String> userName = new ObservableField<>("");
    //密码的绑定
    public ObservableField<String> password = new ObservableField<>("");
    //是否保存密码的确认
    public ObservableBoolean check = new ObservableBoolean(false);

    public LoginViewModel(@NonNull Application application, DataRepository repository) {
        super(application, repository);
        userName.set(model.getUserName());
        password.set(model.getPassword());
        check.set(model.getCheck());
    }

    //登录按钮的点击事件
    public BindingCommand loginOnClickCommand = new BindingCommand(new BindingAction() {
        @Override
        public void call() {
            login();
        }
    });

    public void login() {
        if (TextUtils.isEmpty(userName.get()) || TextUtils.isEmpty(password.get())) {
            ToastUtils.showShort("请输入用户名密码");
            return;
        }

        model.login(0)
                .compose(RxSchedulers.io_main())
                .as(AutoDispose.autoDisposable(AndroidLifecycleScopeProvider.from((LifecycleOwner) getLifecycleProvider())))
                .subscribe(new DefaultObserver<BaseResponse<LoginResponse<Item>>>(this) {
                    @Override
                    public void onSuccess(BaseResponse<LoginResponse<Item>> result) {
                        if (result.isSuccess()) {
                            if (check.get()) {
                                model.saveUserName(userName.get());
                                model.savePassword(userName.get());
                                model.setCheck(check.get());
                            } else {
                                SPUtils.getInstance().clear();
                            }
                            startActivity(MainActivity.class);
                            finish();
                        }
                    }

                    @Override
                    public void onFailure(Throwable e, String errorMsg) {
                        ToastUtils.showShort(e.toString());
                    }
                });
    }
}
