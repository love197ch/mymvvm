package com.ch.mymvvm.data;

import com.ch.mymvvm.bean.Item;
import com.ch.mymvvm.data.http.BaseResponse;
import com.ch.mymvvm.data.room.AppDatabase;
import com.ch.mymvvm.data.room.entity.MyAddress;
import com.ch.mymvvm.response.LoginResponse;
import com.ch.mymvvm.service.ApiLocal;
import com.ch.mymvvm.service.ApiService;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import io.reactivex.Observable;
import io.reactivex.Single;
import me.goldze.mvvmhabit.base.BaseModel;
import me.goldze.mvvmhabit.utils.SPUtils;

/**
 * MVVM的Model层，统一模块的数据仓库，包含网络数据和本地数据（一个应用可以有多个Repositor）
 */
public class DataRepository extends BaseModel implements ApiService, ApiLocal {
    private volatile static DataRepository INSTANCE = null;
    private final ApiService mHttpDataSource;
    private final AppDatabase mLocalDataSource;

    private DataRepository(@NonNull ApiService httpDataSource,
                           @NonNull AppDatabase localDataSource) {
        this.mHttpDataSource = httpDataSource;
        this.mLocalDataSource = localDataSource;
    }

    public static DataRepository getInstance(ApiService httpDataSource,
                                             AppDatabase localDataSource) {
        if (INSTANCE == null) {
            synchronized (DataRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataRepository(httpDataSource, localDataSource);
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    @Override
    public Observable<BaseResponse<LoginResponse<Item>>> login(int page) {
        return mHttpDataSource.login(page);
    }

    @Override
    public void insertAddress(MyAddress... data) {
        mLocalDataSource.addressDao().insert(data);
    }

    @Override
    public void deleteAllAddress() {
        mLocalDataSource.addressDao().deleteAll();
    }

    @Override
    public Single<List<MyAddress>> getAllAddress() {
        return mLocalDataSource.addressDao().getAll();
    }

    @Override
    public void saveUserName(String userName) {
        SPUtils.getInstance().put("UserName", userName);
    }

    @Override
    public void savePassword(String password) {
        SPUtils.getInstance().put("password", password);
    }

    @Override
    public String getUserName() {
        return SPUtils.getInstance().getString("UserName");
    }

    @Override
    public String getPassword() {
        return SPUtils.getInstance().getString("password");
    }

    @Override
    public boolean getCheck() {
        return SPUtils.getInstance().getBoolean("Check");
    }

    @Override
    public void setCheck(boolean check) {
        SPUtils.getInstance().put("Check", check);
    }
}
