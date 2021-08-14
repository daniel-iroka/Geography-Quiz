package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

private const val TAG = "MainActivity"  // TAG constant refers to the source of the log message(The Activity class)

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton : Button  // This are all properties that will be used to hold the Views
    private lateinit var falseButton :Button
    private lateinit var nextButton :Button
    private lateinit var questionTextView :TextView

    // todo continue from creating a landscape layout - Device Configuration...

    // This is a list holding all the questions being passed to the 'Question Class' as a StringResourceId and a Boolean
    private val questionBank = listOf(
        Question(R.string.question_australia, true),  // these are the actual answers to the question
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true),
        Question(R.string.question_continent, true),
        Question(R.string.question_amazon, false)
    )
    private var currentIndex = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")  // log message shown based on the current state of the Activity
        setContentView(R.layout.activity_main) // this is an auto-generated resource "id" for the layout

        trueButton = findViewById(R.id.true_button)  // This are all properties identifying the views by ids in the MainActivity.kt
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)


        // Event listeners await an event, such as the clicking of a button
        trueButton.setOnClickListener { view : View ->

            checkAnswer(true)
        }
        falseButton.setOnClickListener { view :View ->
            checkAnswer(false)
        }

        /** NEXT BUTTON **/
        nextButton.setOnClickListener {

            // This is a recursive logic that adds the currentIndex + 1, dividing it with Q.size and returning the remainder
            // till it gets to 8 % 8 =  (0 + 1) which will recurse the process
            currentIndex = (currentIndex + 1) % questionBank.size
            showAllQuestions()
        }
        showAllQuestions() // This is added here to so that the first Question will initially appear in the questionsTextView
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }


    // This function will hold all the questionText
    private fun showAllQuestions() {
        val questionTextResId = questionBank[currentIndex].textResId // .textResId parameter specifies we want only the StringResource(The Questions)
        questionTextView.setText(questionTextResId)

    }


    // This function checks the user's Answer(true/false) against the answer(true/false) in the "answer" parameter in the current Question
    private fun checkAnswer(userAnswer:Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResId = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }

        // Toasts are like pop-up messages in an android_app that will appear with a list of options or suggestions
        val toast = Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.TOP,0, 300)
        toast.show()

        /** Toast's class contains - context(message location), The Resource.id and how long the message is to be displayed. **/
    }
}