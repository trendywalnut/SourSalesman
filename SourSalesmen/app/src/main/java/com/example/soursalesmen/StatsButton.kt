package com.example.soursalesmen

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class StatsButton : AppCompatActivity() {

    private var statsButton : ImageButton? = null
    //object for referencing stuff in the main activity
    private var MainAct : MainActivity? = MainActivity()
    //private var stats : RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        statsButton = findViewById(R.id.statsButton)
        //stats = findViewById(R.id.statsMenu)

        //stats!!.visibility = View.INVISIBLE

        statsButton!!.setOnClickListener{
            openMenu()
        }
    }

    private fun openMenu() {
        //stats!!.visibility = View.VISIBLE
        //val money : Int = MainAct!!.clickCount
        val duration = Toast.LENGTH_LONG
        val testText = "Hello toast!"

        val toast = Toast.makeText(applicationContext, testText, duration)
        toast.show()
    }

}