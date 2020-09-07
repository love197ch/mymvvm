package com.ch.mymvvm.service;


import com.ch.mymvvm.bean.Item;
import com.ch.mymvvm.data.http.BaseResponse;
import com.ch.mymvvm.response.LoginResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface ApiService {

//    @FormUrlEncoded
//    @POST("/login")
//    Observable<LoginResponse> login(@Field("key") String key,
//                                    @Field("phone") String phone,
//                                    @Field("passwd") String passwd);

    @GET("/article/list/{page}/json")
    Observable<BaseResponse<LoginResponse<Item>>> login(@Path("page") int page);

}
