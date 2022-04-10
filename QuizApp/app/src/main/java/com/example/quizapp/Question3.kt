package com.example.quizapp

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Question3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question3)

        title = "Question 3"
        val button: Button = findViewById(R.id.nextButton3)
        button.setOnClickListener{
            val i = Intent(this@Question3, Question4::class.java)
            startActivity(i)
        }

    }


}