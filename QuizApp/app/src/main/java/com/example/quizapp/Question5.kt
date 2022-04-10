package com.example.quizapp

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Question5 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question5)

        title = "Question 5"
        val button: Button = findViewById(R.id.nextButton5)
        button.setOnClickListener{
            val i = Intent(this@Question5, MainActivity::class.java)
            startActivity(i)
        }

    }


}