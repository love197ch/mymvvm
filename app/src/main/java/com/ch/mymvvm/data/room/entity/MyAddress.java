package com.ch.mymvvm.data.room.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * @Author: Administrator
 * @Time: 2020/8/18 16:51
 * @Company：ch
 * @Description: 功能描述
 */
@Entity
public class MyAddress {
    @PrimaryKey(autoGenerate = true)
    @NonNull // required
    public int id;

    public long timeship;

    public String longitude;

    public String latitude;

    public long userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimeship() {
        return timeship;
    }

    public void setTimeship(long timeship) {
        this.timeship = timeship;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
