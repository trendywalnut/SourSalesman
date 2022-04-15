package com.example.quizapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Xml
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.data.Question
import com.example.quizapp.data.Quiz
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserException
import org.xmlpull.v1.XmlPullParserFactory
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray
import java.io.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        title = "Quizdle"
        var quiz = readQuizXml()
        if(quiz.date == ""){
            checkServer()
        }

        val statsLayout:RelativeLayout = findViewById(R.id.statsLayout)
        val closeButton:ImageButton = findViewById(R.id.closeStatButton)
        //val statsLayout:RelativeLayout = findViewById(R.id.statsLayout)
        statsLayout.setVisibility(View.GONE)

        val button:Button = findViewById(R.id.questionButton)
        button.setOnClickListener{
            val intent = Intent(this@MainActivity, Question1::class.java)
            quiz = readQuizXml()

            var questionTexts = arrayListOf<String>()
            var questionSubjects = arrayListOf<String>()
            var questionEmojis = arrayListOf<String>()
            var questionAnswers = arrayListOf<String>()
            var questionCorrectIndices = arrayListOf<Int>()
            for(question in quiz.questions){
                Log.d("here", question.text)
                Log.d("here", question.subject)
                Log.d("here", question.emoji)
                questionTexts.add(question.text)
                questionSubjects.add(question.subject)
                questionEmojis.add(question.emoji)
                questionCorrectIndices.add((question.correctIndex))
                for(answer in question.answers){
                    questionAnswers.add(answer)
                }
            }
            //if stored xml == server date
            intent.putExtra("questionNumber", 0)
            intent.putExtra("questionTexts", questionTexts.toTypedArray())
            intent.putExtra("questionSubjects", questionSubjects.toTypedArray())
            intent.putExtra("questionEmojis", questionEmojis.toTypedArray())
            intent.putExtra("questionAnswers", questionAnswers.toTypedArray())
            intent.putExtra("correctIndices", questionCorrectIndices.toIntArray())
            intent.putExtra("correctArray", booleanArrayOf(false, false, false, false, false))

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

    fun checkServer(){
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://quizapp-e4fd3-default-rtdb.firebaseio.com/daily.json")
            .build()


        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    for ((name, value) in response.headers) {
                        //Log.d("header","$name: $value")
                    }
                    var JSONString = response.body!!.string()

                    //Log.d("final output", JSONString)

                    this@MainActivity.runOnUiThread(java.lang.Runnable {
                        var quiz = Quiz()
                        val JSONQuestionArray: JSONArray = JSONObject(JSONString).getJSONArray("questions")
                        for(i in 0 until JSONQuestionArray.length()){
                            var question = Question()
                            val JSONQuestion = JSONQuestionArray.getJSONObject(i)
                            val JSONAnswerArray = JSONQuestion.getJSONArray("answers")
                            for(a in 0 until JSONAnswerArray.length()){
                                question.answers.add(JSONAnswerArray.getString(a))
                            }
                            question.correctIndex = JSONQuestion.getInt("correct_index")
                            question.emoji = JSONQuestion.getString("emoji")
                            question.subject = JSONQuestion.getString("subject")
                            question.text = JSONQuestion.getString("text")
                            quiz.questions.add(question)
                        }
                        writeQuizXml(quiz)
                    })
                }
            }
        })
    }


    fun writeQuizXml(quiz: Quiz){
        val xmlFile = "quizData"
        try {
            val fileOut = applicationContext.openFileOutput(xmlFile, Context.MODE_PRIVATE)
            val xmlSerializer = Xml.newSerializer()
            val writer = StringWriter()

            xmlSerializer.setOutput(writer)
            xmlSerializer.startDocument("UTF-8", true)
            xmlSerializer.startTag(null, "quizData")

            xmlSerializer.startTag(null, "date")
            xmlSerializer.text(quiz.date)
            xmlSerializer.endTag(null, "date")

            for (question in quiz.questions) {
                xmlSerializer.startTag(null, "question")

                xmlSerializer.startTag(null, "text")
                xmlSerializer.text(question.text)
                xmlSerializer.endTag(null, "text")

                xmlSerializer.startTag(null, "subject")
                xmlSerializer.text(question.subject)
                xmlSerializer.endTag(null, "subject")

                xmlSerializer.startTag(null, "emoji")
                xmlSerializer.text(question.emoji)
                xmlSerializer.endTag(null, "emoji")

                for(answer in question.answers){
                    xmlSerializer.startTag(null, "answer")
                    xmlSerializer.text(answer)
                    xmlSerializer.endTag(null, "answer")
                }

                xmlSerializer.startTag(null, "correct_index")
                xmlSerializer.text(question.correctIndex.toString())
                xmlSerializer.endTag(null, "correct_index")

                xmlSerializer.endTag(null, "question")
            }
            xmlSerializer.endTag(null, "quizData")
            xmlSerializer.endDocument()
            xmlSerializer.flush()
            val dataWrite = writer.toString()
            fileOut.write(dataWrite.toByteArray())
            fileOut.close()
        } catch (e: FileNotFoundException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: IllegalArgumentException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: IllegalStateException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
    }

    fun readQuizXml(): Quiz{
        var quiz: Quiz = Quiz()
        val xmlFile = "quizData"
        var data: ByteArray = ByteArray(0)
        try {
            val fileIn = applicationContext.openFileInput(xmlFile)
            data = fileIn.readBytes()
            fileIn.close()
        } catch (e3: FileNotFoundException) {
            // TODO Auto-generated catch block
            e3.printStackTrace()
        } catch (e: IOException) {
            // TODO Auto-generated catch block
            e.printStackTrace()
        }
        var factory: XmlPullParserFactory? = null
        try {
            factory = XmlPullParserFactory.newInstance()
        } catch (e2: XmlPullParserException) {
            // TODO Auto-generated catch block
            e2.printStackTrace()
        }
        factory!!.isNamespaceAware = true
        var xpp: XmlPullParser? = null
        try {
            xpp = factory!!.newPullParser()
        } catch (e2: XmlPullParserException) {
            // TODO Auto-generated catch block
            e2.printStackTrace()
        }
        try {
            var inputStream = ByteArrayInputStream(data)
            xpp!!.setInput(inputStream, null)
        } catch (e1: XmlPullParserException) {
            // TODO Auto-generated catch block
            e1.printStackTrace()
        }
        var eventType = 0
        try {
            eventType = xpp!!.eventType
        } catch (e1: XmlPullParserException) {
            // TODO Auto-generated catch block
            e1.printStackTrace()
        }
        var startTags: ArrayList<String> = arrayListOf<String>()
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_DOCUMENT) {
                println("Start document")
            } else if (eventType == XmlPullParser.START_TAG) {
                println("Start tag " + xpp!!.name)
                if(xpp!!.name != "question"){
                    startTags.add(xpp!!.name);
                }
                else{
                    println("adding question")
                    quiz.questions.add(Question())
                }
            } else if (eventType == XmlPullParser.END_TAG) {
                println("End tag " + xpp!!.name)
                if(xpp!!.name != "question") {
                    startTags.removeLast()
                }
            } else if (eventType == XmlPullParser.TEXT) {
                when(startTags[startTags.lastIndex]){
                    "date" -> quiz.date = xpp!!.text.toString()
                    "text" -> quiz.questions[quiz.questions.lastIndex].text = xpp!!.text.toString()
                    "subject" -> quiz.questions[quiz.questions.lastIndex].subject = xpp!!.text.toString()
                    "emoji" -> quiz.questions[quiz.questions.lastIndex].emoji = xpp!!.text.toString()
                    "answer" -> quiz.questions[quiz.questions.lastIndex].answers.add(xpp!!.text.toString())
                    "correct_index" -> quiz.questions[quiz.questions.lastIndex].correctIndex = xpp!!.text.toString().toInt()
                    else -> Log.d("XML error", startTags[startTags.lastIndex])
                }
            }
            try {
                eventType = xpp!!.next()
            } catch (e: XmlPullParserException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            } catch (e: IOException) {
                // TODO Auto-generated catch block
                e.printStackTrace()
            }
        }
        return quiz
    }
}