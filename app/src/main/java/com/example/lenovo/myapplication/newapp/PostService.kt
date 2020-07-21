package com.example.lenovo.myapplication.newapp

import com.example.lenovo.myapplication.newapp.RootData
import io.reactivex.Observable
import retrofit2.http.*


public interface PostService {
    //单纯使用Retrofit，不加Rxjava的使用
//    @GET("weather_mini")
//    fun  //  此处回调返回的可为任意类型Call<T>，再也不用自己去解析json数据啦！！！
//            getMessage(@Query("city") city: String?): Call<WeatherEntity?>?
    //Retrofit + Rxjava

    @POST("?level=H&size=4&app_key=A1523B92EAEC6D504AE6CBFAE140FE1A")
    @FormUrlEncoded
    open fun getCall(@Field("s") s: String?, @Field("id") id: String?): Observable<RootData?>?
}