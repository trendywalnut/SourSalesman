package com.example.quizapp.data

data class Quiz(var number: Int = -1) {
    var date: String = ""
    var questions: ArrayList<Question> = arrayListOf<Question>()
}