package com.example.soursalesmen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //set up variables
    private var lemonImage : ImageView? = null
    private var scoreText : TextView? = null
    private var moneyString : String? = "$0"
    private var clickCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lemonImage = findViewById(R.id.lemonImage)
        scoreText = findViewById(R.id.scoreText)
        moneyString = resources.getString(R.string.money)

        lemonImage!!.setOnClickListener {
            clickLemonImage()
        }
    }

    private fun clickLemonImage() {
        clickCount++

        updateView()
    }

    private fun updateView() {
        moneyString = "$$clickCount"
    }
}