package com.example.soursalesmen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.*

class MainActivity : AppCompatActivity() {

    //set up variables
    private var lemonImage : ImageView? = null
    public var scoreText : TextView? = null
    private var moneyString : String? = "$0"
    private var clickCount = 0
    private var clickMultiplier = 1
    private var valueMultiplier = 1

    private var timerCooldown : Long = 1000
    private var idleButton : Button? = null
    private var idleLemons = false
    private var clickButton : Button? = null
    private var clickMultiplied = false
    private var valueButton : Button? = null
    private var valueMultiplied = false

    private var statsButton : ImageButton? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lemonImage = findViewById(R.id.lemonImage)
        scoreText = findViewById(R.id.scoreText)
        moneyString = resources.getString(R.string.money)

        statsButton = findViewById(R.id.statsButton)
        //stats = findViewById(R.id.statsMenu)

        //stats!!.visibility = View.INVISIBLE

        statsButton!!.setOnClickListener{
            openMenu()
        }

        idleButton = findViewById(R.id.button1)
        idleButton!!.setOnClickListener{
            clickButton1()
        }
        clickButton = findViewById((R.id.button2))
        clickButton!!.setOnClickListener{
            clickButton2()
        }
        valueButton = findViewById((R.id.button3))
        valueButton!!.setOnClickListener{
            clickButton3()
        }

        lemonImage!!.setOnClickListener {
            clickLemonImage()
        }

    }

    fun initCountDownTimer(time: Long) {
        object : CountDownTimer(time, 1000) {
            override fun onTick(millisUntilFinished: Long) {
            }

            override fun onFinish() {
                idleClick()
                initCountDownTimer(timerCooldown)
            }
        }.start()
    }

    private fun clickButton1(){
        if(!idleLemons){
            initCountDownTimer(timerCooldown)
            idleLemons = true
        }
    }
    private  fun clickButton2(){
        if(!clickMultiplied){
            clickMultiplier += 2
            clickMultiplied = true

        }
    }

    private  fun clickButton3(){
        if(!valueMultiplied){
            valueMultiplier += 1
            valueMultiplied = true
        }
    }

    private fun idleClick(){
        clickCount+=valueMultiplier

        updateView()
    }
    private fun clickLemonImage() {
        clickCount+=clickMultiplier * valueMultiplier

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