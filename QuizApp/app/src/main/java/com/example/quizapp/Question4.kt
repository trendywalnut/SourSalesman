package com.example.quizapp

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Question4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question4)

        title = "Question 4"
        val button: Button = findViewById(R.id.nextButton4)
        button.setOnClickListener{
            val i = Intent(this@Question4, Question5::class.java)
            startActivity(i)
        }

    }


}