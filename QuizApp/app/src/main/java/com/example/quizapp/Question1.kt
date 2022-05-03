package com.example.quizapp

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
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

        //value for performing animations on buttons
        var buttonPressed: Button? = null;

        answer1Button.setText(questionAnswers?.get((questionNumber * 4) + 0).orEmpty())
        answer1Button.setOnClickListener {
            selectedAnswer = 0

            //unpress animation for old answer
            if (buttonPressed != null){
                unpressButton(buttonPressed!!);
            }
            //press animation for new answer
            buttonPressed = answer1Button;
            pressButton(buttonPressed!!);
        }

        answer2Button.setText(questionAnswers?.get((questionNumber * 4) + 1).orEmpty())
        answer2Button.setOnClickListener {
            selectedAnswer = 1

            //unpress animation for old answer
            if (buttonPressed != null){
                unpressButton(buttonPressed!!);
            }
            //press animation for new answer
            buttonPressed = answer2Button;
            pressButton(buttonPressed!!);
        }

        answer3Button.setText(questionAnswers?.get((questionNumber * 4) + 2).orEmpty())
        answer3Button.setOnClickListener {
            selectedAnswer = 2

            //unpress animation for old answer
            if (buttonPressed != null){
                unpressButton(buttonPressed!!);
            }
            //press animation for new answer
            buttonPressed = answer3Button;
            pressButton(buttonPressed!!);
        }

        answer4Button.setText(questionAnswers?.get((questionNumber * 4) + 3).orEmpty())
        answer4Button.setOnClickListener {
            selectedAnswer = 3

            //unpress animation for old answer
            if (buttonPressed != null){
                unpressButton(buttonPressed!!);
            }
            //press animation for new answer
            buttonPressed = answer4Button;
            pressButton(buttonPressed!!);
        }

        title = "Question 1"
        val correctText = "Correct!"
        val incorrectText = "Wrong :("
        val duration = Toast.LENGTH_SHORT
        val nextButton: Button = findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            if(buttonPressed != null) {
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
                    i.putExtra("questionEmojis", questionEmojis)
                    i.putExtra("questionSubjects", questionSubjects)
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
            }else{
                Toast.makeText(applicationContext, "Please select an answer choice", duration).show()
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

    private fun ObjectAnimator.disableViewDuringAnimation(view: View){
        addListener(object : AnimatorListenerAdapter(){
            override fun onAnimationStart(animation: Animator?){
                view.isEnabled = false
            }
            override fun onAnimationEnd(animation: Animator?) {
                view.isEnabled = true;
            }
        })

    }

    private fun pressButton(button: Button){
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 0.9f);
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 0.9f);
        val animator = ObjectAnimator.ofPropertyValuesHolder(button, scaleX, scaleY);

        animator.disableViewDuringAnimation(button);
        animator.start();

        button.setBackgroundColor(Color.GRAY);
    }

    private fun unpressButton(button: Button){
        val scaleX = PropertyValuesHolder.ofFloat(View.SCALE_X, 1f);
        val scaleY = PropertyValuesHolder.ofFloat(View.SCALE_Y, 1f);
        val animator = ObjectAnimator.ofPropertyValuesHolder(button, scaleX, scaleY);

        animator.start();

        button.setBackgroundColor(Color.WHITE);
    }

    override fun onBackPressed() {
        Toast.makeText(applicationContext, "No Backsies ;)", Toast.LENGTH_SHORT).show()
    }
}