package com.ch.mymvvm.data.room;

import android.content.Context;


import com.ch.mymvvm.data.room.dao.AddressDao;
import com.ch.mymvvm.data.room.entity.MyAddress;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

/**
 * @Author: Administrator
 * @Time: 2020/8/18 16:51
 * @Company：ch
 * @Description: 功能描述
 */
@Database(entities = {MyAddress.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract AddressDao addressDao();

    private static AppDatabase mAppDataBase;
    //room数据库初始化
    public static AppDatabase getInstance(Context context) { //实现单例模式
        if (mAppDataBase == null) {
            mAppDataBase = Room.databaseBuilder(context, AppDatabase.class, "data.db")//data.db 是你的数据库名称
                    .build();
        }
        return mAppDataBase;
    }
}
