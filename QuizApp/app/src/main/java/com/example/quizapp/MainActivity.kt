package com.example.quizapp

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Quizdle"

        val statsLayout:RelativeLayout = findViewById(R.id.statsLayout)
        val closeButton:ImageButton = findViewById(R.id.closeStatButton)
        //val statsLayout:RelativeLayout = findViewById(R.id.statsLayout)
        statsLayout.setVisibility(View.GONE)

        val button:Button = findViewById(R.id.questionButton)
        button.setOnClickListener{
            val intent = Intent(this@MainActivity, Question1::class.java)
            intent.putExtra("questionNumber", 0)
            intent.putExtra("questionTexts", resources.getStringArray(R.array.question_texts))
            intent.putExtra("questionSubjects", resources.getStringArray(R.array.question_subjects))
            intent.putExtra("questionEmojis", resources.getStringArray(R.array.question_emojis))
            intent.putExtra("questionAnswers", resources.getStringArray(R.array.question_answers))
            intent.putExtra("correctIndices", resources.getIntArray(R.array.correct_indices))
            intent.putExtra("correctArray", booleanArrayOf(false, false, false, false, false))

            startActivity(intent)
        }

        val statButton:Button = findViewById(R.id.statsButton)
        statButton.setOnClickListener{
            statsLayout.setVisibility(View.VISIBLE)
        }

        closeButton.setOnClickListener{
            statsLayout.setVisibility(View.GONE)
        }
    }
}