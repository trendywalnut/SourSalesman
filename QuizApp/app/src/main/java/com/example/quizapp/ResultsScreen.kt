package com.example.quizapp

import android.R.attr.label
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class ResultsScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)

        val thisIntent = intent
        val correctArray = thisIntent.getBooleanArrayExtra("correctArray")?.copyOf()
        val questionEmojis = thisIntent.getStringArrayExtra("questionEmojis")?.copyOf()
        val questionSubjects = thisIntent.getStringArrayExtra("questionSubjects")?.copyOf()
        val quizzesTaken = thisIntent.getIntExtra("quizzesTaken", 0)

        println("copied arrays")

        title = "Results Screen"
        val rightEmoji = "✔️"
        val wrongEmoji = "❌ "
        val question1 = findViewById<TextView>(R.id.question1)
        val question2 = findViewById<TextView>(R.id.question2)
        val question3 = findViewById<TextView>(R.id.question3)
        val question4 = findViewById<TextView>(R.id.question4)
        val question5 = findViewById<TextView>(R.id.question5)
        val textArray = arrayListOf<TextView>(question1,question2,question3,question4,question5)
        val backButton = findViewById<Button>(R.id.homeButton)
        val copyButton = findViewById<Button>(R.id.copyButton)
        val checkEmojis : Array<String> = Array(5){wrongEmoji}
        println(checkEmojis)


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


        for(i in 0 until textArray.size)
        {
            textArray[i].append("Incorrect");
        }

        var num = 0
        correctArray!!.forEach {
            if(it){
                textArray[num].text = textArray[num].text.dropLast(9)
                textArray[num].append("Correct!")
                checkEmojis[num] = rightEmoji
            }
            num++
        }

        val copyText : String = createCopyText(questionEmojis, checkEmojis)

        backButton.setOnClickListener{

            val i = Intent(this@ResultsScreen, MainActivity::class.java)
            startActivity(i)
        }

        copyButton.setOnClickListener{
            val sharedPreferences = getSharedPreferences("userStats", Context.MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.apply{
                putInt("QUIZZES_TAKEN", quizzesTaken)
            }.apply()

            Toast.makeText(applicationContext, "Data Saved!", Toast.LENGTH_SHORT).show()

            copyTexToClipboard(copyText)
        }

    }

    private fun createCopyText(emojiArray: Array<String>?, checkEmojis :  Array<String>): String {
        //saveData()
        return emojiArray!![0] + checkEmojis[0] + emojiArray[1] + checkEmojis[1] + emojiArray[2] +
                checkEmojis[2] + emojiArray[3] + checkEmojis[3] + emojiArray[4] + checkEmojis[4]
    }

    private fun copyTexToClipboard(copyText : String) {
        val clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clipData = ClipData.newPlainText("text", copyText)
        clipboardManager.setPrimaryClip(clipData)

        Toast.makeText(this, "Results Copied!", Toast.LENGTH_LONG).show()
    }



}