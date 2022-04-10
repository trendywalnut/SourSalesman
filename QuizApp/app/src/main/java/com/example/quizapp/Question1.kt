package com.example.quizapp

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Question1 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question1)

        title = "Question 1"
        val button: Button = findViewById(R.id.nextButton1)
        button.setOnClickListener{
            val i = Intent(this@Question1, Question2::class.java)
            startActivity(i)
        }

    }


}