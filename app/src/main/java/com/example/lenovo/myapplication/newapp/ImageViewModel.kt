package com.example.lenovo.myapplication.newapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lenovo.myapplication.newapp.BaseStatus.BaseObserver

class ImageViewModel : ViewModel() {
    val image: MutableLiveData<RootData>
    private val idx: Int

    fun doRequestByRxRetrofit(id: Int) {
        RetrofitManager.getInstance().requestService.getCall("App.CDN.GetDocInfoById", id.toString())
                ?.compose(RxSchedulers.io_main())
                ?.subscribe(object : BaseObserver<RootData?>() {
                    override fun onSuccess(result: RootData?) {
                        image.value = result
                    }

                    override fun onFailure(e: Throwable?, errorMsg: String?) {}
                })
    }

    init {
        image = MutableLiveData()
        idx = 0
    }
}