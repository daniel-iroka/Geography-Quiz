package com.example.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

// Here we have set a "string" key for out inte
private const val EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true"
private const val EXTRA_ANSWER_IS_SHOWN = "com.bignerdranch.android.geoquiz.answer_shown"


class CheatActivity : AppCompatActivity() {

    private lateinit var answerTextView : TextView
    private lateinit var showAnswerButton : Button
    private var answerIsTrue = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)

        // This retrieves the value from the extra and stores it in a variable...
        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)
        answerTextView = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)


        // show Answer button
        showAnswerButton.setOnClickListener {

            // displays the answer for the current question in CheatActivity
            val answerText = when {
                answerIsTrue -> R.string.true_button
                else -> R.string.false_button
            }
            answerTextView.setText(answerText)
            setAnswerShownResult(true)
        }

    }

    // This sends data back to the parent activity telling if the user checked an answer to any Question
    private fun setAnswerShownResult(isAnswerShown:Boolean) {
        val data = Intent().apply {
            putExtra(EXTRA_ANSWER_IS_SHOWN, isAnswerShown)
        }

        // setResult() tells the state of how the child activity ended...
        setResult(Activity.RESULT_OK, data)
    }


    // passing the extras to the intent
    // The extras refer to arbitrary data we pass along with the intent
    companion object {
        fun newIntent(packageContext:Context , answerIsTrue : Boolean): Intent {
            return Intent(packageContext, CheatActivity::class.java).apply {
                putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
            }
        }
    }
}