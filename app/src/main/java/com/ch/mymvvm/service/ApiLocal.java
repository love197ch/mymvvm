package com.ch.mymvvm.service;

import com.ch.mymvvm.data.room.entity.MyAddress;

import java.util.List;

import androidx.room.Delete;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Single;

/**
 * @Author: Administrator
 * @Time: 2020/9/2 14:22
 * @Company：ch
 * @Description: 功能描述
 */
public interface ApiLocal {
    void insertAddress(MyAddress... data);

    Single<List<MyAddress>> getAllAddress();

    void deleteAllAddress();

    /**
     * 保存用户名
     */
    void saveUserName(String userName);

    /**
     * 保存用户密码
     */

    void savePassword(String password);

    /**
     * 获取用户名
     */
    String getUserName();

    /**
     * 获取用户密码
     */
    String getPassword();

    boolean getCheck();

    void setCheck(boolean check);
}
