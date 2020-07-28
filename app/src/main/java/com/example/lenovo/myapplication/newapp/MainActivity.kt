package com.example.lenovo.myapplication.newapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.lenovo.myapplication.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_picture.setOnClickListener {
            val intent = Intent(this,Main3Activity::class.java)
            startActivity(intent)
            //finish()
        }
        btn_joke.setOnClickListener {
            val intent = Intent(this,Main2Activity::class.java)
            startActivity(intent)
        }
    }
}
