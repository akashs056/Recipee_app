package com.example.recepieapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Test
        Handler(Looper.getMainLooper()).postDelayed(
            {
                startActivity(Intent(this,HomeActivity::class.java))
                finish()
            },2000
        )

    }
}