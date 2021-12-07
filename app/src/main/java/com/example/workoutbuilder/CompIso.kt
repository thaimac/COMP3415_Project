package com.example.workoutbuilder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

//Author: Tyler McEwen
//Purpose: To give functionality to the compound vs isolation movements button and link its corresponding xml

class CompIso : AppCompatActivity() {

    private lateinit var back: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comp_iso)

        back = findViewById(R.id.back)

        back.setOnClickListener { view: View ->
            val intent = Intent(this, KnowledgeBase::class.java)
            startActivity(intent)
        }
    }
}