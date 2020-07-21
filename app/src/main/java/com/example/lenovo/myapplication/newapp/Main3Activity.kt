package com.example.lenovo.myapplication.newapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.bumptech.glide.Glide
import com.example.lenovo.myapplication.R
import com.example.lenovo.myapplication.databinding.ActivityMain3Binding
import io.reactivex.Observer
import io.reactivex.disposables.Disposable


class Main3Activity : AppCompatActivity() {
    private var mViewModel: ImageViewModel? = null
    private var mBinding: ActivityMain3Binding? = null
    @BindView(R.id.click_me_BN)
    lateinit var clickMeBN: Button

    @BindView(R.id.result_TV)
    lateinit var resultTV: ImageView

    @BindView(R.id.text_Num)
    lateinit var text_Num: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main3);
        //setContentView(R.layout.activity_main3)
        ButterKnife.bind(this)
        mViewModel = ViewModelProvider(
                this, AndroidViewModelFactory(application)
        ).get(ImageViewModel::class.java)
        mViewModel!!.image.observe(this,object :androidx.lifecycle.Observer<RootData>{
            override fun onChanged(@Nullable t: RootData?) {
                mBinding?.imageBean= t
            }

        })
        //doRequestByRxRetrofit();
    }
    @OnClick(R.id.click_me_BN)
    open fun onClick(): Unit {
        val aNum = Integer.valueOf(text_Num!!.text.toString())
        if (aNum >= 1 && aNum <= 11) {
            mViewModel?.doRequestByRxRetrofit(aNum)
            //doRequestByRxRetrofit(aNum)
        } else {
            Toast.makeText(this@Main3Activity, "No Image of This Id", Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * 单纯使用Retrofit的联网请求
     */
//    private fun doRequestByRetrofit() {
//        val retrofit: Retrofit = Retrofit.Builder()
//                .baseUrl("http://wthrcdn.etouch.cn/") //基础URL 建议以 / 结尾
//                .addConverterFactory(GsonConverterFactory.create()) //设置 Json 转换器
//                .build()
//        val weatherService = retrofit.create(WeatherService::class.java)
//        val call: Call<WeatherEntity?>? = weatherService.getMessage("北京")
//        if (call != null) {
//            call.enqueue(object : Callback<WeatherEntity?> {
//                override fun onResponse(call: Call<WeatherEntity?>?, response: Response<WeatherEntity?>) {
//                    //测试数据返回
//                    val weatherEntity: WeatherEntity? = response.body()
//                    if (weatherEntity != null) {
//                        Log.e("TAG", "response == " + weatherEntity.data.ganmao)
//                    }
//                }
//
//                override fun onFailure(call: Call<WeatherEntity?>?, t: Throwable) {
//                    Log.e("TAG", "Throwable : $t")
//                }
//            })
//        }
//    }

    private fun doRequestByRxRetrofit(id:Int) {
        RetrofitManager.getInstance().requestService.getCall("App.CDN.GetDocInfoById", id.toString())
                ?.compose(RxSchedulers.io_main())
                ?.safeSubscribe(object:Observer<RootData?>{
                    override fun onComplete() {
                        Toast.makeText(this@Main3Activity, "Get Image", Toast.LENGTH_SHORT).show()
                        Log.e("TAG", "onComplete");
                    }

                    override fun onSubscribe(d: Disposable) {

                    }

                    override fun onNext(t: RootData) {
                        val imageUrl: String = t.data?.info?.file_url.toString()
                        resultTV?.let { Glide.with(this@Main3Activity).load(imageUrl).into(it) }
                        //Log.e("TAG", "response == " + t.data.ganmao)
                    }

                    override fun onError(e: Throwable) {
                        Log.e("TAG", "onError=" + e.message);
                    }

                })
    }


}
@BindingAdapter("android:url")
fun setImageUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
            .load(url)
            .into(imageView)
}






