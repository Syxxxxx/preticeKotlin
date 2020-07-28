package com.example.lenovo.myapplication.newapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import com.example.lenovo.myapplication.R
import com.example.lenovo.myapplication.newapp.BaseStatus.BaseObserver
import kotlinx.android.synthetic.main.activity_date_cal.*
import java.util.*

class DateCalActivity : AppCompatActivity() {
    var yearStart:String=""
    var monthStart:String=""
    var dayStart:String=""
    var yearEnd:String=""
    var monthEnd:String=""
    var dayEnd:String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_date_cal)
        date1.init(1999,1,1,object:DatePicker.OnDateChangedListener{
            override fun onDateChanged(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                yearStart=p1.toString()
                monthStart=p2.toString()
                dayStart=p3.toString()
            }
        })
        date2.init(2020,1,1,object:DatePicker.OnDateChangedListener{
            override fun onDateChanged(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
                yearEnd=p1.toString()
                monthEnd=p2.toString()
                dayEnd=p3.toString()
            }
        })
        btn_cal.setOnClickListener {
            RetrofitManager.getInstance().requestService.calDate("App.Common_Date.GetDaysDiff",yearStart,monthStart,dayStart,yearEnd,monthEnd,dayEnd)
                    ?.compose(RxSchedulers.io_main())
                    ?.safeSubscribe(object : BaseObserver<DateData?>(){
                        override fun onSuccess(result: DateData?) {
                            date_res.text = result?.diff.toString()
                        }

                        override fun onFailure(e: Throwable?, errorMsg: String?) {
                            TODO("Not yet implemented")
                        }

                    })
        }
    }
}
