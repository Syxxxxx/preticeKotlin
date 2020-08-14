package com.example.lenovo.myapplication.newapp

class ApiService{
    val postService:PostService
    init {
        val retrofit = RetrofitManager.getInstance().retrofit
        postService = RetrofitManager.getInstance().requestService
    }
}