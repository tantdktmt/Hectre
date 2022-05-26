package com.hectre.hectrelib.presentation

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.hectre.extension.setSafeOnClickListener
import com.hectre.hectrelib.R

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        findViewById<Button>(R.id.button_second).setSafeOnClickListener {
            val intent = Intent(applicationContext, MainLibActivity::class.java)
            startActivity(intent)
        }
    }
}