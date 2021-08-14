package com.example.geoquiz

import androidx.annotation.StringRes // StringRes - parameter is a string resource

// data classes are specifically used for holding data
// This class is the Model view for collecting the bank of questions
data class Question(@StringRes val textResId:Int, val answer:Boolean) {

    // no code
}

data class QuestionNumber(@StringRes val textQuestion:Int) {
    // no code
}