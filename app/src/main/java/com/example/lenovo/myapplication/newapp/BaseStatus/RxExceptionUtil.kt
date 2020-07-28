package com.example.lenovo.myapplication.newapp.BaseStatus

import org.json.JSONException
import retrofit2.adapter.rxjava2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.text.ParseException


/**
 * 异常处理
 */
object RxExceptionUtil {
    fun exceptionHandler(e: Throwable): String {
        var errorMsg = "未知错误"
        if (e is UnknownHostException) {
            errorMsg = "网络不可用"
        } else if (e is SocketTimeoutException) {
            errorMsg = "请求网络超时"
        } else if (e is HttpException) {
            val httpException: HttpException = e as HttpException
            errorMsg = convertStatusCode(httpException)
        } else if (e is ParseException || e is JSONException
                || e is JSONException) {
            errorMsg = "数据解析错误"
        }
        return errorMsg
    }

    private fun convertStatusCode(httpException: HttpException): String {
        val msg: String
        msg = if (httpException.code() >= 500 && httpException.code() < 600) {
            "服务器处理请求出错"
        } else if (httpException.code() >= 400 && httpException.code() < 500) {
            "服务器无法处理请求"
        } else if (httpException.code() >= 300 && httpException.code() < 400) {
            "请求被重定向到其他页面"
        } else {
            httpException.message()
        }
        return msg
    }
}