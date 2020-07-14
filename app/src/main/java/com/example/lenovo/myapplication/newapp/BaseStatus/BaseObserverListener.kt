package com.example.lenovo.myapplication.newapp.BaseStatus

interface BaseObserverListener<T> {
    fun onSuccess(result: T)
    fun onComplete()
    fun onError(e: Throwable?)
    fun onBusinessError(errorBean: ErrorBean?)
}