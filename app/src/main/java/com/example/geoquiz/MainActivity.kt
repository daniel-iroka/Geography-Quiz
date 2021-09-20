package com.example.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast

private const val TAG = "MainActivity"  // source of the message to be displayed on the logs

class MainActivity : AppCompatActivity() {
    private lateinit var trueButton : Button  // This are all properties that will be used to hold the Views
    private lateinit var falseButton :Button
    private lateinit var nextButton :ImageButton
    private lateinit var questionTextView :TextView
    private lateinit var previousButton :ImageButton
    private lateinit var questionNumberTextView :TextView
    private lateinit var skipTextView: TextView


    // SECOND_CHALLENGE

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

    /** MY ADDITIONAL FEATURE **/
    private val questionNumberList = listOf(
        QuestionNumber(R.string.no1_question),
        QuestionNumber(R.string.no2_question),
        QuestionNumber(R.string.no3_question),
        QuestionNumber(R.string.no4_question),
        QuestionNumber(R.string.no5_question),
        QuestionNumber(R.string.no6_question),
        QuestionNumber(R.string.no7_question),
        QuestionNumber(R.string.no8_question)
    )
    private var secondIndex = 0
    private var initialScore = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // this is an auto-generated resource "id" for the layout
        Log.d(TAG, "onCreate(Bundle?) called")

        trueButton = findViewById(R.id.true_button)  // This are all properties identifying the views by ids in the MainActivity.kt
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)
        previousButton = findViewById(R.id.previous_button)
        questionNumberTextView = findViewById(R.id.question_number)
        skipTextView = findViewById(R.id.skip_textViewButton)


        // Event listeners await an event, such as the clicking of a button
        trueButton.setOnClickListener { view : View ->

            checkAnswer(true)
            calculateAnswer(true)

            // this code disables the button after it is pressed
            if(trueButton.isPressed) {
                trueButton.isEnabled = false
                falseButton.isEnabled = false
                previousButton.isEnabled = false
            }
        }
        falseButton.setOnClickListener { view :View ->
            checkAnswer(false)
            calculateAnswer(false)

            // this disables the button after it is pressed
            if(falseButton.isPressed) {
                falseButton.isEnabled = false
                trueButton.isEnabled = false
                previousButton.isEnabled = false
            }
        }


        // Button to skip a Question
        skipTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            secondIndex = (secondIndex + 1) % questionNumberList.size
            showAllQuestions()
            showQuestionNumbers()


        }


        //  Next Button
        nextButton.setOnClickListener {

            // This is a recursive logic that adds the currentIndex + 1, dividing it with Q.size and returning the remainder
            // till it gets to 8 % 8 =  (0 + 1) which will recurse the process
            currentIndex = (currentIndex + 1) % questionBank.size
            secondIndex = (secondIndex + 1) % questionNumberList.size
            showAllQuestions()
            showQuestionNumbers()

            // re-enables the button
            if (nextButton.isPressed) {
                trueButton.isEnabled = true
                falseButton.isEnabled = true
            }
        }
        showAllQuestions() // This is added here to so that the first Question will initially appear in the questionsTextView
        showQuestionNumbers()


        /** Challenge 2 Completed - Add a previous button to go back on a question **/
        /** PREVIOUS BUTTON **/
        previousButton.setOnClickListener {
            if (currentIndex == 0) {
                showAllQuestions()
            } else {
                currentIndex = (currentIndex - 1) % questionBank.size
            }
            showAllQuestions()

            // FOR THE QUESTION NUMBERS
            if (secondIndex == 0 ) {
                showQuestionNumbers()
            } else {
                secondIndex = (secondIndex - 1) % questionNumberList.size
            }
            showQuestionNumbers()
        }

        /** Challenge 1 Completed - Make the questionTextView Clickable **/
        questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            showAllQuestions()
        }

    }


    // This function will hold all the questionText
    private fun showAllQuestions() {
        val questionTextResId = questionBank[currentIndex].textResId // .textResId parameter specifies we want only the StringResource(The Questions)
        questionTextView.setText(questionTextResId)

    }

    // This function will hold all the Question Numbers
    private fun showQuestionNumbers() {
        val questionNumberId = questionNumberList[secondIndex].textQuestion
        questionNumberTextView.setText(questionNumberId)
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

    /** Challenge : for all of the quiz questions, display a Toast with a percentage score
    for the quiz. Good luck! **/
    private fun calculateAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        // To calculate the score
        if(userAnswer == correctAnswer) {
            initialScore = (initialScore + 5)
        } else {
            initialScore
        }
        initialScore.toString()

        // display total points
        if (currentIndex == 7) {
            val toast1 = Toast.makeText(this, "your score is $initialScore out of 40 points.", Toast.LENGTH_LONG)
            toast1.setGravity(Gravity.TOP, 0, 300)
            toast1.show()
        }

    }
}