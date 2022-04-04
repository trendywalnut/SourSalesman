package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Quizdle"

        val button:Button = findViewById(R.id.questionButton)
        button.setOnClickListener{
            val intent = Intent(this@MainActivity, Question1::class.java)
            startActivity(intent)
        }
    }
}