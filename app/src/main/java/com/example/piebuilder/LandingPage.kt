package com.example.piebuilder

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class LandingPage : AppCompatActivity() {

    private lateinit var learnMore: Button
    private lateinit var enterApp: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_page)

        learnMore = findViewById(R.id.learn_more)
        enterApp = findViewById(R.id.enter)

        learnMore.setOnClickListener { view: View ->
            val intent = Intent(this, LearnMore::class.java)
            startActivity(intent)
            Log.i("about us", "learn more clicked")
        }
        enterApp.setOnClickListener { view: View ->
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            Log.i("enter app", "enter app clicked")
        }
    }
}
