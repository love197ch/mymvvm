package com.ch.mymvvm.data.room.dao;




import com.ch.mymvvm.data.room.entity.MyAddress;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import io.reactivex.Single;

/**
 * @Author: Administrator
 * @Time: 2020/8/18 16:57
 * @Company：ch
 * @Description: 功能描述
 */
@Dao
public interface AddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
        //@Insert = 插入, onConflict = 如果冲突  OnConflictStrategy.REPLACE = 如果冲突就替换
    void insert(MyAddress... data); //添加了插入注释后，这个方法就可以当做插入数据的方法使用

    @Update
    void update(MyAddress... data);// @Update = 更新

    @Delete
    void delete(MyAddress... data);// @Delete = 删除

    @Query("select * from MyAddress")
    Single<List<MyAddress>> getAll();

    @Query("delete from MyAddress")
    void deleteAll();
}
