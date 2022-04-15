package com.example.quizapp.data

data class Question (var text: String = "") {
    var subject: String = ""
    var emoji: String = ""
    //var answerCount: Int = 0
    var answers: ArrayList<String> = arrayListOf<String>()
    var correctIndex: Int = 0
}