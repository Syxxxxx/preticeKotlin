package com.example.lenovo.myapplication.newapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lenovo.myapplication.R
import com.example.lenovo.myapplication.newapp.BaseStatus.BaseHttpResponse
import com.example.lenovo.myapplication.newapp.BaseStatus.BaseObserver
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {
    private var repository = Repository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        btn_joke.setOnClickListener {
            repository.getData()?.safeSubscribe(
                    object :BaseObserver<JokeData?>(){
                        override fun onSuccess(result: JokeData?) {
                            if (result != null) {
                                joke_res.text= result.joke?.get(0)?.get(0)
                            }
                        }

                        override fun onFailure(e: Throwable?, errorMsg: String?) {
                            TODO("Not yet implemented")
                        }
                    }
            )
//            RetrofitManager.getInstance().requestService.getJoke()
//                    ?.compose(RxSchedulers.io_main())
//                    ?.safeSubscribe(object: BaseObserver<JokeData?>(){
//                        override fun onSuccess(result: JokeData?) {
//                            if (result != null) {
//                                joke_res.text= result.joke?.get(0)?.get(0)
//                            }
//                        }
//
//                        override fun onFailure(e: Throwable?, errorMsg: String?) {
//
//                        }
//
//                    })
        }
    }
}


