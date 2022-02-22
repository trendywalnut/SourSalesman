package com.example.soursalesmen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    //set up variables
    private var lemonImage : ImageView? = null
    public var scoreText : TextView? = null
    private var moneyString : String? = "$0"
    public var clickCount = 0

    private var statsButton : ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lemonImage = findViewById(R.id.lemonImage)
        scoreText = findViewById(R.id.scoreText)
        moneyString = resources.getString(R.string.money)

        lemonImage!!.setOnClickListener {
            clickLemonImage()
        }
        statsButton = findViewById(R.id.statsButton)
        //stats = findViewById(R.id.statsMenu)

        //stats!!.visibility = View.INVISIBLE

        statsButton!!.setOnClickListener{
            openMenu()
        }
    }

    private fun clickLemonImage() {
        clickCount++

        updateView()
    }

    private fun updateView() {
        val scoreText: TextView = findViewById(R.id.scoreText)
        moneyString = "$ $clickCount"

        scoreText.text = moneyString
    }

    private fun openMenu() {
        //stats!!.visibility = View.VISIBLE
        val money : String = "$ $clickCount"
        val duration = Toast.LENGTH_SHORT

        val text : String = "Total money made: " + money

        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }
}