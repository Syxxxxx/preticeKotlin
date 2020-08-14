package com.example.lenovo.myapplication.newapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DateViewModel : ViewModel() {
    val mDate: MutableLiveData<DateData>



    init {
        mDate = MutableLiveData()
    }


}