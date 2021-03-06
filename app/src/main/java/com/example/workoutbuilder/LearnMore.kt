package com.example.workoutbuilder

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

//Author: Tyler McEwen
//Purpose: To render the xml for the Learn More section of the app and listen for clicks on the back button
class LearnMore : AppCompatActivity() {

    private lateinit var back: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.learn_more)

        back = findViewById(R.id.back)

        back.setOnClickListener { view: View ->
            val intent = Intent(this, LandingPage::class.java)
            startActivity(intent)
        }
    }
}