package com.ch.mymvvm.app;

import com.ch.mymvvm.data.DataRepository;
import com.ch.mymvvm.data.http.RetrofitUtils;
//import com.ch.mymvvm.data.room.AppDatabase;
import com.ch.mymvvm.data.room.AppDatabase;
import com.ch.mymvvm.service.ApiService;
import com.ch.mymvvm.utils.StaticVariable;

import me.goldze.mvvmhabit.utils.SPUtils;
import me.goldze.mvvmhabit.utils.Utils;


/**
 * 注入全局的数据仓库，可以考虑使用Dagger2。（根据项目实际情况搭建，千万不要为了架构而架构）
 * Created by goldze on 2019/3/26.
 */
public class Injection {
    public static DataRepository provideDemoRepository() {
        StaticVariable.URL = SPUtils.getInstance().getString("url", "https://www.wanandroid.com");
        //网络API服务
        ApiService apiService = RetrofitUtils.getInstance().create(ApiService.class);
        //本地数据源
        AppDatabase localDataSource = AppDatabase.getInstance(Utils.getContext());
        //两条分支组成一个数据仓库
        return DataRepository.getInstance(apiService, localDataSource);
    }
}
