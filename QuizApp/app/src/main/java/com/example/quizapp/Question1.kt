package com.example.quizapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Question1 : AppCompatActivity() {
    var selectedAnswer = 4
    var paused = false
    var registerPause = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question1)
        val thisIntent = getIntent()
        val questionNumber = thisIntent.getIntExtra("questionNumber", 0)
        val questionTexts = thisIntent.getStringArrayExtra("questionTexts")
        val questionSubjects = thisIntent.getStringArrayExtra("questionSubjects")
        val questionEmojis = thisIntent.getStringArrayExtra("questionEmojis")
        val questionAnswers = thisIntent.getStringArrayExtra("questionAnswers")
        val correctIndices = thisIntent.getIntArrayExtra("correctIndices")
        val correctArray = thisIntent.getBooleanArrayExtra("correctArray")?.copyOf()

        val questionNumberView: TextView = findViewById(R.id.question_number)
        questionNumberView.setText("Q" + (questionNumber + 1).toString())

        val questionSubject: TextView = findViewById(R.id.question_subject)
        questionSubject.setText(questionSubjects?.get(questionNumber).orEmpty())

        val questionText: TextView = findViewById(R.id.question_text)
        questionText.setText(questionTexts?.get(questionNumber).orEmpty())

        val answer1Button: Button = findViewById(R.id.answer1)
        val answer2Button: Button = findViewById(R.id.answer2)
        val answer3Button: Button = findViewById(R.id.answer3)
        val answer4Button: Button = findViewById(R.id.answer4)

        answer1Button.setText(questionAnswers?.get((questionNumber * 4) + 0).orEmpty())
        answer1Button.setOnClickListener {
            selectedAnswer = 0
        }

        answer2Button.setText(questionAnswers?.get((questionNumber * 4) + 1).orEmpty())
        answer2Button.setOnClickListener {
            selectedAnswer = 1
        }

        answer3Button.setText(questionAnswers?.get((questionNumber * 4) + 2).orEmpty())
        answer3Button.setOnClickListener {
            selectedAnswer = 2
        }

        answer4Button.setText(questionAnswers?.get((questionNumber * 4) + 3).orEmpty())
        answer4Button.setOnClickListener {
            selectedAnswer = 3
        }


        title = "Question 1"
        val correctText = "Correct!"
        val incorrectText = "Wrong :("
        val duration = Toast.LENGTH_SHORT
        val nextButton: Button = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            registerPause = false
            Log.d("selected: ", selectedAnswer.toString())
            Log.d("indices: ", correctIndices?.get(questionNumber).toString())
            if (selectedAnswer == correctIndices?.get(questionNumber)) {
                val toast = Toast.makeText(applicationContext, correctText, duration)
                toast.show()
                Log.d("wow: ", "selected correct")
                correctArray?.set(questionNumber, true)
            }else{
                val toast = Toast.makeText(applicationContext, incorrectText, duration)
                toast.show()
            }
            Log.d("level: ", questionNumber.toString())
            for (i in 0..4) {
                Log.d("correct: ", correctArray?.get(i).toString())
            }
            if (questionNumber == 4) {
                val i = Intent(this@Question1, ResultsScreen::class.java)
                i.putExtra("correctArray", correctArray)
                startActivity(i)
            } else {
                val i = Intent(this@Question1, Question1::class.java)

                i.putExtra("questionNumber", questionNumber + 1)
                i.putExtra("questionTexts", questionTexts)
                i.putExtra("questionSubjects", questionSubjects)
                i.putExtra("questionEmojis", questionEmojis)
                i.putExtra("questionAnswers", questionAnswers)
                i.putExtra("correctIndices", correctIndices)
                i.putExtra("correctArray", correctArray)

                startActivity(i)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if(registerPause){
            paused = true
            Log.d("pause", "paused")
        }
    }
}