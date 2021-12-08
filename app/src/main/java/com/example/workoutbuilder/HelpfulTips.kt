package com.example.workoutbuilder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity

// Author: Lauryn Gowanlock
// Purpose: To render xml file for helpful tips page and set up the back button
class HelpfulTips : AppCompatActivity() {

    private lateinit var backBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.helpful_tips)

        backBtn = findViewById(R.id.backTipsBtn)

        backBtn.setOnClickListener { view: View ->
            val intent = Intent(this, KnowledgeBase::class.java)
            startActivity(intent)
        }

    }

}
