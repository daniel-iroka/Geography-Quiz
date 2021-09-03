package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders

private const val TAG = "MainActivity"  // TAG constant refers to the source of the log message(The Activity class)
private const val KEY_INDEX = "index"

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton : Button  // This are all properties that will be used to hold the Views
    private lateinit var falseButton :Button
    private lateinit var nextButton :Button
    private lateinit var cheatButton: Button
    private lateinit var questionTextView :TextView



    // We use by lazy here because we want to use 'val' and we only want the QuizViewModel to occur
    // when we access it, which is a safe process for accessing ViewModel before the activity is created
    private val quizViewModel: QuizViewModel by lazy {

        // ViewModel provider creates and returns a new instance of the QuizViewModel Activity
        // When the devices rotates, it returns that already created instance instead of creating a new one all again
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")  // log message shown based on the current state of the Activity
        setContentView(R.layout.activity_main) // this is an auto-generated resource "id" for the layout

        // This assigns the savedInstanceState to the current Index if a value with "index"exists BUT
        // if not it assigns it to 0
        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex


        trueButton = findViewById(R.id.true_button)  // This are all properties identifying the views by ids in the MainActivity.kt
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        cheatButton = findViewById(R.id.cheat_button)
        questionTextView = findViewById(R.id.question_text_view)


        // Event listeners await an event, such as the clicking of a button
        trueButton.setOnClickListener {

            checkAnswer(true)
        }
        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        // NEXT button
        nextButton.setOnClickListener {

            quizViewModel.moveToNext()
            updateQuestions()
        }

        // This is the cheatButton
        cheatButton.setOnClickListener {

            // intent is an object that an activity can use to communicate with the Android OS
            // 'this' argument refers to the where the activity class can be found
            // While the 'Class'(second argument) specifies the activity we want the class we are sending to the activity manager
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity, answerIsTrue)
            startActivity(intent)
        }

        updateQuestions() // This is added here to so that the first Question will initially appear in the questionsTextView
    }

    // Lifecycle callbacks
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

    // overriding onSavedInstanceState() to store data outside the activity as (activity record) when the OS destroys to free
    // resources
    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSavedInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
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
    private fun updateQuestions() {
        val questionTextResId = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResId)

    }


    // This function checks the user's Answer(true/false) against the answer(true/false) in the "answer" parameter in the current Question
    private fun checkAnswer(userAnswer:Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer

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