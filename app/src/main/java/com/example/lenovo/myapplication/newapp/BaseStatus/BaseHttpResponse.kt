package com.example.lenovo.myapplication.newapp.BaseStatus

class BaseHttpResponse<T> {
    var ret = 0//具体含义由后端定义
    var msg //请求结果的描述-成功/失败/参数错误等
            : String? = null
    var data //实际有用的数据
            : T? = null
}