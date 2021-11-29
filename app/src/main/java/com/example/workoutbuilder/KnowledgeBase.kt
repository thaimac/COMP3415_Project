package com.example.workoutbuilder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class KnowledgeBase : AppCompatActivity() {

    private lateinit var back: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.knowledge_base)

        back = findViewById(R.id.back)

        back.setOnClickListener { view: View ->
            val intent = Intent(this, LandingPage::class.java)
            startActivity(intent)
        }
    }
}