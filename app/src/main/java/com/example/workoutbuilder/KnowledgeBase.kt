package com.example.workoutbuilder

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ScrollView
import androidx.appcompat.app.AppCompatActivity

class KnowledgeBase : AppCompatActivity() {

    private lateinit var back: Button
    private lateinit var compIso: Button
    private lateinit var setsRepsRest: Button
    private lateinit var tipsBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.knowledge_base)

        back = findViewById(R.id.back)
        compIso = findViewById(R.id.comp_iso)
        setsRepsRest = findViewById(R.id.sets_reps_rest)
        tipsBtn = findViewById(R.id.helpfulTipsBtn)

        back.setOnClickListener { view: View ->
            val intent = Intent(this, LandingPage::class.java)
            startActivity(intent)
        }

        compIso.setOnClickListener{ view: View ->
            val intent = Intent(this, CompIso::class.java)
            startActivity(intent)
        }

        setsRepsRest.setOnClickListener{ view: View ->
            val intent = Intent(this, SetsRepsRest::class.java)
            startActivity(intent)
        }

        tipsBtn.setOnClickListener{ view: View ->
            val intent = Intent(this, HelpfulTips::class.java)
            startActivity(intent)
        }
    }
}