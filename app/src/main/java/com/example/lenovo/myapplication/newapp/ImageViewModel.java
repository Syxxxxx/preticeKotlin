package com.example.lenovo.myapplication.newapp;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import androidx.lifecycle.ViewModel;



public class ImageViewModel extends ViewModel {
    private MutableLiveData<RootData> mImage;
    private int idx;
    public ImageViewModel() {
        mImage = new MutableLiveData<>();
        idx = 0;
    }
    public MutableLiveData<RootData> getImage() {
        return mImage;
    }

    public void doRequestByRxRetrofit(int id) {
        RetrofitManager.getInstance().getRequestService().getCall("App.CDN.GetDocInfoById", String.valueOf(id))
                .compose(RxSchedulers.io_main())
                .subscribe(new Observer<RootData>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RootData rootData) {
                        mImage.setValue(rootData);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
