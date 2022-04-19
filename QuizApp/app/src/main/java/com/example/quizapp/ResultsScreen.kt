package com.example.quizapp

import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ResultsScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val thisIntent = intent
        val correctArray = thisIntent.getBooleanArrayExtra("correctArray")?.copyOf()
        val questionEmojis = thisIntent.getStringArrayExtra("questionEmojis")?.copyOf()
        val questionSubjects = thisIntent.getStringArrayExtra("questionSubjects")?.copyOf()

        title = "Results Screen"
        val question1 = findViewById<TextView>(R.id.question1)
        val question2 = findViewById<TextView>(R.id.question2)
        val question3 = findViewById<TextView>(R.id.question3)
        val question4 = findViewById<TextView>(R.id.question4)
        val question5 = findViewById<TextView>(R.id.question5)
        val textArray = arrayListOf<TextView>(question1,question2,question3,question4,question5)
        val backButton = findViewById<Button>(R.id.homeButton)

        question1.text = questionSubjects!![0]
        question2.text = questionSubjects!![1]
        question3.text = questionSubjects!![2]
        question4.text = questionSubjects!![3]
        question5.text = questionSubjects!![4]

        question1.append((questionEmojis!![0]))
        question2.append((questionEmojis!![1]))
        question3.append((questionEmojis!![2]))
        question4.append((questionEmojis!![3]))
        question5.append((questionEmojis!![4]))



        textArray.forEach{
            it.append("Incorrect")
        }

        var num = 0
        correctArray!!.forEach {
            if(it){
                textArray[num].text = textArray[num].text.dropLast(9)
                textArray[num].append("Correct!")
            }
            num++
        }


        backButton.setOnClickListener{
            val i = Intent(this@ResultsScreen, MainActivity::class.java)
            startActivity(i)
        }

    }


}