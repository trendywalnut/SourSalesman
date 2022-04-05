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