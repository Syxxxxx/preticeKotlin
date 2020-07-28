package com.example.lenovo.myapplication.newapp.BaseStatus

import io.reactivex.Observer
import io.reactivex.disposables.Disposable


abstract class BaseObserver<T> : Observer<BaseHttpResponse<T>?> {
    override fun onNext(response: BaseHttpResponse<T>) {
        //在这边对 基础数据 进行统一处理  举个例子：
        if (response.ret=== 200) {
            response.data?.let { onSuccess(it) }
        } else {
            onFailure(null, response.msg)
        }
    }

    override fun onError(e: Throwable) { //服务器错误信息处理
        onFailure(e, RxExceptionUtil.exceptionHandler(e))
    }

    override fun onComplete() {}
    override fun onSubscribe(d: Disposable) {}
    abstract fun onSuccess(result: T)
    abstract fun onFailure(e: Throwable?, errorMsg: String?)

    companion object {
        private const val TAG = "BaseObserver"
    }
}