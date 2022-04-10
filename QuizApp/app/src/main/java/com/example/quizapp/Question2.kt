package com.example.quizapp

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Question2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question2)

        title = "Question 2"
        val button: Button = findViewById(R.id.nextButton2)
        button.setOnClickListener{
            val i = Intent(this@Question2, Question3::class.java)
            startActivity(i)
        }

    }


}