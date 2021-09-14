package com.example.geoquiz

import androidx.lifecycle.ViewModel

// source of the message to be displayed in the logcat
private const val TAG = "QuizViewModel"

// ViewModel class - The purpose of the ViewModel is to acquire and keep the information that is necessary for an Activity or a Fragment.
// That means that a ViewModel will not be destroyed if the owner is destroyed for a configuration change(Rotation)
// So ViewModel simply "saves" or "takes" the state of a lifecycle into account
class QuizViewModel : ViewModel() {


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
    var currentIndex = 0
    var isCheater = false


    // Computed properties to return the Answer and text for the currentQuestion
    val currentQuestionAnswer :Boolean
        get() = questionBank[currentIndex].answer
    val currentQuestionText : Int
        get() = questionBank[currentIndex].textResId // .textResId parameter specifies we want only the StringResource(The Questions)


    // function to move to the next question
    fun moveToNext() {

        // This is a recursive logic that adds the currentIndex + 1, dividing it with Q.size and returning the remainder
        // till it gets to 8 % 8 =  (0 + 1) which will recurse the process
        currentIndex = (currentIndex + 1) % questionBank.size
    }
}

/** A ViewModel survives configuration changes and is destroyed only when its associated activity is
finished. **/