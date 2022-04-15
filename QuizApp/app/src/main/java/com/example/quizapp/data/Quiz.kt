package com.example.quizapp.data

data class Quiz(var date: String = "") {
    var questions: ArrayList<Question> = arrayListOf<Question>()
}