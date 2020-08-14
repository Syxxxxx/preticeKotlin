package com.example.lenovo.myapplication.newapp

import com.example.lenovo.myapplication.newapp.BaseStatus.BaseHttpResponse
import com.example.lenovo.myapplication.newapp.BaseStatus.BaseObserver
import io.reactivex.Observable

class Repository {
    private val postApi : ApiService = ApiService()
    fun getData(): Observable<BaseHttpResponse<JokeData?>>? {
        return postApi.postService.getJoke()
    }
}