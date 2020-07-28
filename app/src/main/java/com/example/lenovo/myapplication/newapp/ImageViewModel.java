package com.example.lenovo.myapplication.newapp;

import androidx.lifecycle.MutableLiveData;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

import androidx.lifecycle.ViewModel;

import com.example.lenovo.myapplication.newapp.BaseStatus.BaseHttpResponse;
import com.example.lenovo.myapplication.newapp.BaseStatus.BaseObserver;

import org.jetbrains.annotations.Nullable;


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
                .subscribe(new BaseObserver<RootData>() {
                    @Override
                    public void onSuccess(RootData result) {
                        mImage.setValue(result);
                    }
                    @Override
                    public void onFailure(@Nullable Throwable e, @Nullable String errorMsg) {

                    }

                });
    }
}
